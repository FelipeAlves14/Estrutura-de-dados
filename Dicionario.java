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
    private Object binarySearch(Object element, int min, int max, int tam){
        int meio = tam / 2;
        if(data[meio] == element) return data[meio];
        if(min > max) throw new BaseException("Tem esse trem aq não KKKKKKKKKKKKKKKKK");
        if(element > data[meio]) binarySearch(element, meio+1, max, tam/2);
        else binarySearch(element, min, meio, tam/2);
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
    public Object findElement(Object element){
        return binarySearch(element, 0, n-1, n);
    }
}