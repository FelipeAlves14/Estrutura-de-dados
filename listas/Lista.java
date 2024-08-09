public class Lista{
    public class ObjectNaoEncontrado extends RuntimeException{
        public ObjectNaoEncontrado(String err){
            super(err);
        }
    }
    public class ListaVazia extends RuntimeException{
        public ListaVazia(String err){
            super(err);
        }
    }
    private Object[] datas = new Object[10];
    private int n = 0;
    private void updateCapacity(){
        Object[] new_datas = new Object[n * 2];
            for(int i = 0; i < n; ++i) new_datas[i] = datas[i];
            n *= 2;
            datas = new_datas;
    }
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public boolean isFirst(Object obj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        return obj == datas[0];
    }
    public boolean isLast(Object obj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        return obj == datas[n - 1];
    }
    public Object first(){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        return datas[0];
    }
    public Object last(){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        return datas[n - 1];
    }
    public Object before(Object obj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        if(isFirst(obj)) return last();
        for(int i = 1; i < n; ++i){
            if(datas[i] == obj) return datas[i - 1];
        }
        throw new ObjectNaoEncontrado("Tem isso aq não em KKKKKKKKKKKKKKKKKK");
    }
    public Object after(Object obj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        if(isLast(obj)) return first();
        for(int i = 0; i < n - 1; ++i){
            if(datas[i] == obj) return datas[i + 1];
        }
        throw new ObjectNaoEncontrado("Tem esse elemento aq não em KKKKKKKKKKKKKKKKKK");
    }
    public Object replaceElement(Object oldObj, Object newObj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        for(int i = 0; i < n; ++i){
            if(datas[i] == oldObj){
                datas[i] = newObj;
                return oldObj;
            }
        }
        throw new ObjectNaoEncontrado("Tem esse elemento aq não em KKKKKKKKKKKKKKKKKK");
    }
    public void swapElements(Object obj1, Object obj2){
        if(isEmpty() || n == 1) throw new ListaVazia("Tem nem dois elemento ai KKKKKKKKKKKKKKKKKKKKK");
        int ind1 = -1, ind2 = -1;
        for(int i = 0; i < n; ++i){
            if(datas[i] == obj1){
                ind1 = i;
                break;
            }
        }
        for(int i = 0; i < n; ++i){
            if(datas[i] == obj2){
                ind2 = i;
                break;
            }
        }
        if(ind1 != -1 && ind2 != -1){
            Object aux = datas[ind1];
            datas[ind1] = datas[ind2];
            datas[ind2] = aux;
        }
        else throw new ObjectNaoEncontrado("Tem esse elemento aq não em KKKKKKKKKKKKKKKKKK");
    }
    public void insertBefore(Object before, Object obj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        if(n >= datas.length - 1) updateCapacity();
        if(before == datas[0]) insertFirst(obj);
        else{
            for(int i = 1; i < n; ++i){
                if(datas[i] == before){
                    for(int j = n; j >= i; --j) datas[j + 1] = datas[j];
                    datas[i] = obj;
                    ++n;
                    return;
                }
            }
            throw new ObjectNaoEncontrado("Tem esse elemento aq não em KKKKKKKKKKKKKKKKKK");
        }
    }
    public void insertAfter(Object after, Object obj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        if(n >= datas.length - 1) updateCapacity();
        if(after == datas[n - 1]) insertLast(obj);
        else{
            for(int i = 1; i < n; ++i){
                if(datas[i] == after){
                    for(int j = n; j > i; --j) datas[j + 1] = datas[j];
                    datas[i] = obj;
                    ++n;
                    return;
                }
            }
            throw new ObjectNaoEncontrado("Tem esse elemento aq não em KKKKKKKKKKKKKKKKKK");
        }
    }
    public void insertFirst(Object obj){
        if(n >= datas.length - 1) updateCapacity();
        for (int i = n; i > 0; --i) datas[i + 1] = datas[i];
        datas[0] = obj;
        ++n;
    }
    public void insertLast(Object obj){
        if(n >= datas.length - 1) updateCapacity();
        datas[n++] = obj;
    }
    public Object remove(Object obj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        for (int i = 0; i < n; ++i){
            if(datas[i] == obj){
                for(int j = i; j < n; ++j) datas[i] = datas[i + 1];
                --n;
                return obj;
            }
        }
        throw new ObjectNaoEncontrado("Tem esse elemento aq não em KKKKKKKKKKKKKKKKKK");
    }
}