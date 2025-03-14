package Algoritmos;

import logica.Contenedor1;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoundRobin {

    private Queue<Contenedor1> contenedorQueue;
    long tiempo0;
    int numContenedores;
    List<Contenedor1> contenedores;
    long totalTornaroundTime;
    long totalResponseTime;
    double tornaroundTimeP;
    double responseTimeP;
    String resultadoE;
    DockerClient client;
    DefaultDockerClientConfig clientConfig;
    int quantum;
    int tiempoActual;
    

    public RoundRobin(List<Contenedor1> contenedores, int quantum) {
        this.contenedorQueue = new LinkedList<>();

        this.numContenedores = contenedores.size();
        this.contenedores = contenedores;
        this.tiempoActual = 0;
        for (Contenedor1 cont : contenedores) {
            contenedorQueue.offer(cont);
        }
        this.quantum = quantum;
        resultadoE = "\n\n---------------------estos son los resultados---------------------\n\n";
        

        clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost("tcp://localhost:2375").build();
        this.client = DockerClientBuilder.getInstance(clientConfig).build();
    }

    public String crearImagen(Contenedor1 cont) {
        try {
            File dockerfile = new File("src/main/java/dockerfiles/Dockerfile");
            File baseDir = dockerfile.getParentFile();

            // Build image
            String imageId = client.buildImageCmd(baseDir)
                    .withDockerfile(dockerfile)
                    .withBuildArg("VECTOR", cont.getComando())
                    .withTag(cont.getNombreI())
                    .exec(new BuildImageResultCallback() {
                        @Override
                        public void onNext(BuildResponseItem item) {
                            System.out.println("" + item.getStream());
                            super.onNext(item);
                        }
                    }).awaitImageId();

            System.out.println("Image created with ID: " + imageId);

            return imageId;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Image verificarImagen(Contenedor1 cont) {
        List<Image> images = client.listImagesCmd().exec();
        if (images.isEmpty()) {
            return null;
        } else {
            for (Image im : images) {
                if (im.getRepoTags() != null) {
                    for (String tag : im.getRepoTags()) {
                        if (tag.contains(cont.getNombreI())) {
                            return im;
                        }
                    }
                }
            }
            return null;
        }
    }

    public String ejecutarContenedor(Contenedor1 cont) {
        if (verificarImagen(cont) == null) {
            crearImagen(cont);
        }
        return crearContenedor(cont);
    }

    public String crearContenedor(Contenedor1 cont) {
        DockerClient client = DockerClientBuilder.getInstance(clientConfig).build();
        Image image = verificarImagen(cont);
        CreateContainerResponse container = client.createContainerCmd(image.getId())
                .withHostConfig(HostConfig.newHostConfig().withAutoRemove(true))
                .exec();
        cont.setContainerId(container.getId());
        client.startContainerCmd(container.getId()).exec();
        return container.getId();
    }

    private void startContainer(Contenedor1 container) {
        if (container.getTiempoEstimadoIngresado() == container.getTiempoRestante()) {
            container.setTiempoInicio(tiempoActual);
            container.setResponseTime(tiempoActual - container.getTiempoLlegada());
            totalResponseTime+=container.getResponseTime();
        }
        ejecutarContenedor(container);
        System.out.println("Tiempo " + tiempoActual + ": Iniciando contenedor " + container.getNombreI());
        resultadoE += "Tiempo " + tiempoActual + ": Iniciando contenedor " + container.getNombreI()+"\n";
    }

    private void stopContainer(Contenedor1 container) {
        client.stopContainerCmd(container.getContainerId()).exec();
        System.out.println("Tiempo " + tiempoActual + ": Deteniendo contenedor " + container.getNombreI());
        resultadoE += "Tiempo " + tiempoActual + ": Deteniendo contenedor " + container.getNombreI()+"\n";
    }

    public String getResultadoE() {
        return resultadoE;
    }

    public void setResultadoE(String resultadoE) {
        this.resultadoE = resultadoE;
    }

    public double getTornaroundTimeP() {
        return tornaroundTimeP;
    }

   

    public double getResponseTimeP() {
        return responseTimeP;
    }

 
    
    
    
    

    public void ejecutarContenedores() throws InterruptedException {
        
        long count = 0;
        resultadoE = "";
        Contenedor1 currentContainer = null;

        while (!contenedorQueue.isEmpty() || currentContainer != null) {
            if (currentContainer == null && !contenedorQueue.isEmpty()) {
                currentContainer = contenedorQueue.poll();
                startContainer(currentContainer);
                
            }

            if (currentContainer != null) {
                long tiempoEjecucion = Math.min(currentContainer.getTiempoRestante(), quantum);
                Thread.sleep(tiempoEjecucion*1000);
                tiempoActual += tiempoEjecucion;
                currentContainer.setTiempoRestante(currentContainer.getTiempoRestante() - tiempoEjecucion);

                if (currentContainer.getTiempoRestante() == 0) {
                    currentContainer.setTiempoFinal(tiempoActual);
                    currentContainer.setTornaroundTime(currentContainer.getTiempoFinal() - currentContainer.getTiempoLlegada());
                    totalTornaroundTime += currentContainer.getTornaroundTime();
                    System.out.println("Tiempo " + this.tiempoActual + ": Contenedor " + currentContainer.getNombreI() + " completado");
                    resultadoE += "Tiempo " + this.tiempoActual + ": Contenedor " + currentContainer.getNombreI() + " completado\n";
                    currentContainer = null;
                    count++;
                } else {
                    contenedorQueue.offer(currentContainer);
                    currentContainer = null;
                }
            }
        }

        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Contenedor1 cont : contenedores) {
            resultadoE += "\nResultados: " + cont.getNombreI() + "\n";
            resultadoE += "Tiempo llegada: " + cont.getTiempoLlegada() + "\n";
            resultadoE += "Tiempo inicial: " + cont.getTiempoInicio() + "\n";
            resultadoE += "Tiempo final: " + cont.getTiempoFinal() + "\n";
            resultadoE += "Tiempo estimado Ingresado: " + cont.getTiempoEstimadoIngresado() + "\n";
            resultadoE += "TornaroundTime: " + cont.getTornaroundTime() + "\n";
            resultadoE += "ResponseTime: " + cont.getResponseTime() + "\n\n";
        }

        tornaroundTimeP = totalTornaroundTime / (double)contenedores.size();
        responseTimeP = totalResponseTime / (double)contenedores.size();
        resultadoE += "TornaroundTimePromedio: " + tornaroundTimeP + "\n";
        resultadoE += "ResponseTimePromedio: " + responseTimeP + "\n\n";
    }

    public static void main(String[] args) {
        /*Contenedor1 cont1 = new Contenedor1(1, "sleep2", "sleep 2", 0, 2);
        Contenedor1 cont2 = new Contenedor1(2, "sleep3", "sleep 3", 1, 3);
        Contenedor1 cont3 = new Contenedor1(3, "sleep4", "sleep 4", 2, 4);*/
        
        Contenedor1 cont1 = new Contenedor1(1, "sleep1", "sleep 1", 0, 1);
        Contenedor1 cont2 = new Contenedor1(2, "sleep4", "sleep 4", 1, 4);
        Contenedor1 cont3 = new Contenedor1(3, "sleep6", "sleep 6", 2, 6);
        Contenedor1 cont4 = new Contenedor1(3, "sleep2", "sleep 2", 3, 2);

        List<Contenedor1> conts = new ArrayList<>();
        conts.add(cont1);
        conts.add(cont2);
        conts.add(cont3);
        conts.add(cont4);

        RoundRobin scheduler = new RoundRobin(conts, 2);
        
        try {
            scheduler.ejecutarContenedores();
        } catch (InterruptedException ex) {
            Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(scheduler.getResultadoE());
    }
}
