public class ArvoreDePesquisa{
    public class NotFoundException extends RuntimeException{
        public NotFoundException(String err){
            super(err);
        }
    }
    public class No{
        No parent, lChild, rChild;
        Object element;
        public No(Object elem){
            element = elem;
        }
        public Object getElement(){
            return element;
        }
    }
    @Override
    public String toString(){
        String arvore = "";
        inOrder(raiz, arvore);
        return arvore;
    }

    private void inOrder(No atual, String arvore){
        if(hasLeft(atual)) inOrder(leftChild(atual), arvore);
        System.out.print(atual.element + " ");
        if(hasRight(atual)) inOrder(rightChild(atual), arvore);
    }
    private int n = 0;
    private No raiz;
    public int size(){
        return n;
    }
    public int height(No raiz){
        if(isExternal(raiz)) return depth(raiz);
        int leftHeight = 0, rightHeight = 0;
        if(hasLeft(raiz)) leftHeight = height(leftChild(raiz));
        if(hasRight(raiz)) rightHeight = height(rightChild(raiz));
        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public No root(){
        if(isEmpty()) throw new NotFoundException("arvore vazia, nao ha elementos");
        return raiz;
    }
    public No parent(No no){
        if(isRoot(no)) throw new NotFoundException("Elemento é o raíz nao ha pai");
        return no.parent;
    }
    public No leftChild(No no){
        if(isEmpty()) throw new NotFoundException("arvore vazia, nao ha elementos");
        return no.lChild;
    }
    public No rightChild(No no){
        if(isEmpty()) throw new NotFoundException("arvore vazia, nao ha elementos");
        return no.rChild;
    }
    public boolean hasLeft(No no){
        if(isEmpty()) throw new NotFoundException("arvore vazia, nao ha elementos");
        return no.lChild != null;
    }
    public boolean hasRight(No no){
        if(isEmpty()) throw new NotFoundException("arvore vazia, nao ha elementos");
        return no.rChild != null;
    }
    public boolean isInternal(No no){
        if(isEmpty()) throw new NotFoundException("arvore vazia, nao ha elementos");
        return no.lChild != null || no.rChild != null;
    }
    public boolean isExternal(No no){
        if(isEmpty()) throw new NotFoundException("arvore vazia, nao ha elementos");
        return no.lChild == null && no.rChild == null;
    }
    public boolean isRoot(No no){
        if(isEmpty()) throw new NotFoundException("arvore vazia, nao ha elementos");
        return no == raiz;
    }
    public int depth(No no){
        int d = 0;
        while(!isRoot(no)){
            ++d;
            no = parent(no);
        }
        return d;
    }
    public No find(Object elem, No no){
        if((int)no.element == (int)elem) return no;
        if(hasLeft(no) && (int)elem < (int)no.element) return find(elem, leftChild(no));
        else if(hasRight(no) && (int)elem > (int)no.element) return find(elem, rightChild(no));
        return no;
    }
    public void insert(Object elem){
        No novo = new No(elem);
        if(isEmpty()){
            raiz = novo;
            ++n;
            return;
        }
        No pai = find(elem, raiz);
        if(pai.element == elem) throw new NotFoundException("Elemento ja existe na arvore");
        else{
            if((int)elem < (int)pai.element) pai.lChild = novo;
            else pai.rChild = novo;
            novo.parent = pai;
        }
        ++n;
    }
    public No remove(Object elem){
        if(isEmpty()) throw new NotFoundException("arvore vazia, nao ha elementos");
        No no = find(elem, raiz);
        No retorno = no;
        if(no.element != elem) throw new NotFoundException("Elemento nao existe na arvore");
        No pai = parent(no);
        if(isExternal(no)){
            No esquerdo = leftChild(pai);
            if(esquerdo == no) pai.lChild = null;
            else pai.rChild = null;
        }
        else if(hasLeft(no) && !hasRight(no) || !hasLeft(no) && hasRight(no)){
            if(hasLeft(no)){
                No filhoEsquerdo = leftChild(no);
                filhoEsquerdo.parent = pai;
                pai.lChild = filhoEsquerdo;
            }
            else{
                No filhoDireito = rightChild(no);
                filhoDireito.parent = pai;
                pai.rChild = filhoDireito;
            }
        }
        else{
            No aux = rightChild(no);
            while(hasLeft(aux)) aux = leftChild(aux);
            no.element = aux.element;
            if(hasRight(aux)){
                aux.element = rightChild(aux).element;
                aux.rChild = null;
            }
            else aux = null;
        }
        --n;
        return retorno;
    }
}