public class Dicionario{
    public class Avaliable{ // Classe avaliable para inserir no lugar de objetos removidos
        public Avaliable(){}
    }
    public class NO_SUCH_KEY extends RuntimeException{ // Criei vergonha E uma exceção normal
        public NO_SUCH_KEY(String err){
            super(err);
        }

        @Override
        public String toString(){ // não funciona como deve, mas ta ai :D
            return "Espaço disponível";
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
    public int[] keys(){
        int[] keys = new int[data.length];
        for(int i = 0; i < data.length; ++i) keys[i] = i;
        return keys;
    }
    public Object[] Elements(){
        if(isEmpty()) throw new NO_SUCH_KEY("Dicionário ta vazio po KKKKKKKKKKKKKKKKKKKKKK");
        Object[] elements = new Object[n];
        int index = 0;
        for(int i = 0; i < data.length; ++i){
            if(data[i] != null && !(data[i] instanceof Avaliable)) elements[index++] = data[i];
        }
        return elements;
    }
    public Object findElement(int key){
        if(isEmpty()) throw new NO_SUCH_KEY("Dicionário ta vazio po KKKKKKKKKKKKKKKKKKKKKK");
        if(key >= data.length) throw new NO_SUCH_KEY("Tem essa chave aq não po KKKKKKKKKKKKKKKKKKKKKK");
        return data[key];
    }
    public void insert(Object obj){
        if(n == data.length - 1){ // rehash
            Object[] new_data = new Object[data.length * 2]; // novo array pra armazenar elementos
            Object[] elements = Elements(); // array só com os elementos
            for(int i = 0; i < n; ++i){ // inserção normal com nova função 
                int element = (int)elements[i];
                int index = element % new_data.length;
                while(new_data[index] != null) index = ++index % new_data.length;
                new_data[index] = elements[i];       
            }
            data = new_data;
        }
        int element = (int)obj; // conversão pra calcular na função
        int index = element % data.length; // função provisória, quiser trocar fique a vontade
        while(data[index] != null && !(data[index] instanceof Avaliable)) index = ++index % data.length;
        /*  
        se tiver vazio pula o while e é dentro
        caso contrário vai modificando o index pelo Linear Probing até achar um espaço vazio
        */
        data[index] = obj;
        ++n;
    }
    public Object removeElement(int key){
        if(isEmpty()) throw new NO_SUCH_KEY("Dicionário ta vazio po KKKKKKKKKKKKKKKKKKKKKK");
        if(key >= n) throw new NO_SUCH_KEY("Tem essa chave aq não po KKKKKKKKKKKKKKKKKKKKKK");
        Object retorno = data[key];
        if(retorno instanceof Avaliable || retorno == null) throw new NO_SUCH_KEY("Tem nada aq não po vai remover oq KKKKKKKKKKKKKKKKKKKKKK");
        data[key] = new Avaliable();
        --n;
        return retorno;
    }
}