public class Dicionario{
    public class Avaliable{ // Classe avaliable para inserir no lugar de objetos removidos
        public Avaliable(){}
    }
    public class NO_SUCH_KEY extends RuntimeException{ // Criei vergonha E uma exceção normal
        public NO_SUCH_KEY(String err){
            super(err);
        }
    }
    private int n = 0;
    private Object[] data = new Object[10]; // vai começar com 10 elementos pq sim :D

    // Notei q não faz sentido ter busca binária se não tiver ordenado, mas deixei ai comentado caso c queira mexer

    // private Object binarySearch(int key, int min, int max){
    //     int meio = (min + max) / 2;
    //     if(data[meio] == data[key]) return data[key];
    //     if(min > max) throw new NO_SUCH_KEY("Tem esse trem aq não KKKKKKKKKKKKKKKKK");
    //     if(data[key] > data[meio]) binarySearch(key, meio, max);
    //     else binarySearch(key, min, meio-1);
    // }
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public Object[] keys(){
        Object[] keys = new Object[data.length];
        for(int i = 0; i < data.length; ++i) keys[i] = i;
        return keys;
    }
    public Object[] Elements(){
        Object[] elements = new Object[n];
        int index = 0;
        for(int i = 0; i < data.length; ++i){
            if(data[i] != null && !(data[i] instanceof Avaliable)) elements[index++] = data[i];
        }
        return elements;
    }
    public Object findElement(int key){
        // tirei a busca binária, confio no seu potencial :D
    }
    public void insert(Object obj){
        int element = (int)obj; // conversão pra calcular na função
        int index = data.length % element; // função provisória, quiser trocar fique a vontade
        if(n == data.length - 1){
            // rehash (preguiça de fazer)
        }
        while(data[index] != null && !(data[index] instanceof Avaliable)) index = ++index % data.length;
        /*  
        se tiver vazio pula o while e é dentro
        caso contrário vai modificando o index pelo Linear Probing até achar um espaço vazio
        */
        data[index] = obj;
    }
    public Object removeElement(Object obj){
        // confio no seu potencial :D
    }
}