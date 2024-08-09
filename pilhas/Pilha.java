public class Pilha{
    public class EPilhaVazia extends RuntimeException{
        public EPilhaVazia(String err){
            super(err);
        }
    }
    private Object[] datas;
    private int t = -1;
    public Pilha(){
        datas = new Object[10];
    }
    public int size(){
        return t + 1;
    }
    public boolean isEmpty(){
        return t == -1;
    }
    public Object top(){
        if(isEmpty()) throw new EPilhaVazia("A pilha está vazia!");
        return datas[t];
    }
    public void push(Object obj){
        if(t == datas.length - 1){
            Object[] new_data = new Object[datas.length * 2];
            for(int i = 0; i <= t; ++i) new_data[i] = datas[i];
            datas = new_data;
        }
        else if(t + 2 == datas.length / 4){
            Object[] new_data = new Object[datas.length / 2];
            for (int i = 0; i <= t; ++i) new_data[i] = datas[i];
            datas = new_data;
        }
        datas[++t] = obj;
    }
    public Object pop(){
        if(isEmpty()) throw new EPilhaVazia("A pilha está vazia!");
        if(t == datas.length / 4){
            Object[] new_data = new Object[datas.length / 2];
            for (int i = 0; i <= t; ++i) new_data[i] = datas[i];
            datas = new_data;
        }
        return datas[t--];
    }
}