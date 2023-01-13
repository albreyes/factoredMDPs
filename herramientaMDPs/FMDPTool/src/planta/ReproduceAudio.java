package planta;


import java.io.*;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;


public class ReproduceAudio {

  AudioInputStream audioInputStream;
  SourceDataLine sourceDataLine;
  boolean stopPlayback = false;
  AudioFormat audioFormat;
  String ruta;

    public ReproduceAudio(int numRecomendacion){
        try {
            // Se obtiene un Clip de sonido
          /*  Clip sonido = AudioSystem.getClip();
            // Se carga con un fichero wav*/
            File pathAudio = new File(".");
            String pathString = pathAudio.getCanonicalPath()+"/Audios/";

            switch(numRecomendacion){
                case 0:
                     pathString= pathString + "abrirFWV.wav";
                break;
                case 1:
                     pathString= pathString +"cerrarFWV.wav";
                break;
                case 2:
                     pathString= pathString +"abrirMSV.wav";
                break;
                case 3:
                     pathString= pathString +"cerrarMSV.wav";
                break;
                case 4:
                     pathString= pathString +"nula.wav";
                break;
            }
            ruta = pathString;
       
        } catch (Exception e) {
            System.out.println("" + e);
        }

            playAudio();

    }

   private void playAudio() {
    try{
      File soundFile =
                   new File(ruta);
      audioInputStream = AudioSystem.
                  getAudioInputStream(soundFile);
      audioFormat = audioInputStream.getFormat();
      System.out.println(audioFormat);

      DataLine.Info dataLineInfo =
                          new DataLine.Info(
                            SourceDataLine.class,
                                    audioFormat);

      sourceDataLine =
             (SourceDataLine)AudioSystem.getLine(
                                   dataLineInfo);

      new PlayThread().start();
    }catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }

  class PlayThread extends Thread{
  byte tempBuffer[] = new byte[10000];

  public void run(){
    try{
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();

      int cnt;

      while((cnt = audioInputStream.read(
           tempBuffer,0,tempBuffer.length)) != -1
                       && stopPlayback == false){
        if(cnt > 0){

          sourceDataLine.write(
                             tempBuffer, 0, cnt);
        }//end if
      }//end while

      sourceDataLine.drain();
      sourceDataLine.close();

      stopPlayback = false;
    }catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }
}


}
