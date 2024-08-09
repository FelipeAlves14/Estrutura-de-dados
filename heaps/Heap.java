public class Heap{
    public class NotFoundException extends RuntimeException{
        public NotFoundException(String err){
            super(err);
        }
    }
    private Object[] data = new Object[10];
    private int n = 0;
    public int size(){
        return n;
    }
    public int height(){
        return depth(n);
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public Object root(){
        if(isEmpty()) throw new NotFoundException("Heap vazio, não há elementos");
        return data[1];
    }
    public Object parent(int index){
        if(isRoot(data[index])) throw new NotFoundException("Elemento é o raíz não há pai");
        return data[index / 2];
    }
    public Object leftChild(int index){
        if(isExternal(index)) throw new NotFoundException("Elemento é um folha, não há filhos");
        return data[index * 2];
    }
    public Object rightChild(int index){
        if(isExternal(index)) throw new NotFoundException("Elemento é um folha, não há filhos");
        return data[index * 2 + 1];
    }
    public boolean hasLeft(int index){
        return leftChild(index) != null;
    }
    public boolean hasRight(int index){
        return rightChild(index) != null;
    }
    public boolean isInternal(int index){
        if(isEmpty()) throw new NotFoundException("Heap vazio, não há elementos");
        return index * 2 < data.length ? data[index * 2] != null : false;
    }
    public boolean isExternal(int index){
        if(isEmpty()) throw new NotFoundException("Heap vazio, não há elementos");
        return index * 2 < data.length ? data[index * 2] == null && data[index * 2 + 1] == null : true;
    }
    public boolean isRoot(Object o){
        if(isEmpty()) throw new NotFoundException("Heap vazio, não há elementos");
        return o == root();
    }
    public int depth(int index){
        int d = 0;
        while(index > 1){
            ++d;
            index /= 2;
        }
        return d;
    }
    public void insert(Object o){
        if(n == data.length - 1){
            Object[] new_data = new Object[data.length * 2];
            for(int i = 0; i <= n; ++i) new_data[i] = data[i];
            data = new_data;
        }
        data[++n] = o;
        int index = n;
        while(!isRoot(data[index]) && (int)parent(index) > (int)data[index]){
            data[index] = parent(index);
            data[index / 2] = o;
            index /= 2;
        }
    }
    public Object removeMin(){
        Object retorno = root();
        data[1] = data[n];
        data[n--] = null;
        int index = 1;
        while(isInternal(index) && ((int)leftChild(index) > (int)data[index] || (int)rightChild(index) > (int)data[index])){
            if(hasRight(index) && (int)rightChild(index) < (int)leftChild(index)){
                Object aux = data[index];
                data[index] = data[index * 2 + 1];
                data[index * 2 + 1] = aux;
                index = index * 2 + 1;
            }
            else{
                Object aux = data[index];
                data[index] = data[index * 2];
                data[index * 2] = aux;
                index *= 2;
            }
        }
        return retorno;
    }
}