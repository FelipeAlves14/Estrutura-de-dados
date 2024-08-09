public class Fila{
    public class EFilaVazia extends RuntimeException{
        public EFilaVazia(String err){
            super(err);
        }
    }
    private Object[] datas;
    private int i = 0, f = 0, n = 10;
    public Fila(){
        datas = new Object[n];
    }
    public int size(){
        return (n - i + f) % n;
    }
    public boolean isEmpty(){
        return i == f;
    }
    public Object first(){
        if(isEmpty()) throw new EFilaVazia("A fila está vazia!");
        return datas[i];
    }
    public void push(Object obj){
        if(f == n - 1){
            Object[] new_data = new Object[n * 2];
            for (int j = 0; j < n; ++j){
                new_data[j] = datas[i];
                i = ++i % n;
            }
            f = size();
            i = 0;
            n *= 2;
            datas = new_data;
        }
        datas[f] = obj;
        f = ++f % n;
    }
    public Object pop(){
        if(isEmpty()) throw new EFilaVazia("A fila está vazia!");
        return datas[i++];
    }
}