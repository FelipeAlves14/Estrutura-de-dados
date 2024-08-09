public class FilaDPilha{
    public class EFilaVazia extends RuntimeException{
        public EFilaVazia(String err){
            super(err);
        }
    }
    private Pilha datas = new Pilha();
    private Pilha ord_datas = new Pilha();
    public FilaDPilha(){}
    public int size(){
        return datas.size();
    }
    public boolean isEmpty(){
        return datas.isEmpty();
    }
    public Object first(){
        if(datas.isEmpty()) throw new EFilaVazia("Ta vazia maluco vai printar oq KKKKKKKKKKKKKKKKKKK");
        int size = datas.size();
        for(int i = 0; i < size; ++i) ord_datas.push(datas.pop());
        Object obj = ord_datas.top();
        size = ord_datas.size();
        for(int i = 0; i < size; ++i) datas.push(ord_datas.pop());
        return obj;
    }
    public void push(Object obj){
        datas.push(obj);
    }
    public Object pop(){
        if(datas.isEmpty()) throw new EFilaVazia("Ta vazia maluco vai popar oq KKKKKKKKKKKKKKKKKKK");
        int size = datas.size();
        for(int i = 0; i < size; ++i) ord_datas.push(datas.pop());
        Object obj = ord_datas.pop();
        size = ord_datas.size();
        for(int i = 0; i < size; ++i) datas.push(ord_datas.pop());
        return obj;
    }
}