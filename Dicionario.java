public class Dicionario{
    public class Avaliable{ // Classe avaliable para inserir no lugar de objetos removidos
        public Avaliable(){}
    }
    public class BaseException extends RuntimeException{ // Exceção pra tudo (preguiça de criar mais de uma)
        public BaseException(String err){
            super(err);
        }
    }
    private int n = 0;
    private Object[] data = new Object[10]; // vai começar com 10 elementos pq sim :D
    private Object binarySearch(int key, int min, int max){
        int meio = (min + max) / 2;
        if(data[meio] == data[key]) return data[key];
        if(min > max) throw new BaseException("Tem esse trem aq não KKKKKKKKKKKKKKKKK");
        if(data[key] > data[meio]) binarySearch(key, meio, max);
        else binarySearch(key, min, meio-1);
    }
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
        Object elements = new Object[n];
        int index = 0;
        for(int i = 0; i < data.length; ++i){
            if(data[i] != null && !data[i].equals(Avaliable)) elements[index++] = data[i];
        }
        return elements;
    }
    public Object findElement(int key){
        return binarySearch(key, 0, n-1);
    }
}