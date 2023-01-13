package aprendizaje;
// clase Nodos para aprendizaje bayesiano con elvira
public class Nodos {

  private String id, desc, tipoVar;
  private String claseNodo;
  private String noEstados;
  private String estados;

  public Nodos(String id, String desc, int noE){
    this.id="node "+id+" (finite-states)";
    this.desc="// "+desc;
    this.claseNodo="kind-of-node = "+"chance"+";";
    this.tipoVar="type-of-variable = "+"finite-states"+";";
    this.noEstados="num-states = "+noE+";";
    estados=estados(noE);
  }

// para valores  cualitativos o discretos
  public Nodos(String[] at){
  this.id="node "+at[0]+" (finite-states)";
  this.desc="// "+at[0];
  this.claseNodo="kind-of-node = "+"chance"+";";
  this.tipoVar="type-of-variable = "+"finite-states"+";";
  this.noEstados="num-states = "+(at.length-1)+";";
  estados=estados(at);
}


  public Nodos(String id,int noE){
    this.id="node "+id+" (finite-states)";
    this.desc="// "+id;
    this.claseNodo="kind-of-node = "+"chance"+";";
    this.tipoVar="type-of-variable = "+"finite-states"+";";
    this.noEstados="num-states = "+noE+";";
    estados=estados(noE);
  }

  public Nodos(String id, String claseNodo, String tipoVar, String desc, int noE){

    this.desc="// "+desc;
    this.claseNodo="kind-of-node = "+claseNodo+";";
    this.tipoVar="type-of-variable = "+tipoVar+";";
    this.id="node "+id+" ("+tipoVar+")";
    this.noEstados="num-states = "+noE+";";
    estados=estados(noE);
  }

  private String estados(int s){
    String cadena="states = (";
    for(int i=0;i<s;i++)
      if(i<s-1)
      cadena=cadena+"s"+i+" ";
      else
        cadena=cadena+"s"+i+");";
    return cadena;
  }
  //  estados cualitativos o discretos
  private String estados(String[] s){
    String cadena="states = (";
    for(int i=1;i<s.length;i++)
      if(i<s.length-1)
        cadena=cadena+s[i]+" ";
      else
        cadena=cadena+s[i]+");";
     // cadena=cadena+s[i]+" ";
    return cadena;
  }

  public String getDatos(){
    String out = desc +"\n"+
        id + "{\n" +
        claseNodo + "\n" +
        tipoVar + "\n" +
        noEstados + "\n" +
        estados + "\n" +
        "}";
  return out;
  }
}