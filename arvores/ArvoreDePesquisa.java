package arvores;
public class ArvoreDePesquisa{
    public class BaseException extends RuntimeException{
        public BaseException(String err){
            super(err);
        }
    }
    public class No{
        protected No parent, lChild, rChild;
        protected Object element;
        protected int fb = 0;
        protected String cor = "Rubro";
        protected int duploNegro = 0;
        public No(Object elem){
            element = elem;
        }
        public No(Object elem, String color){
            element = elem;
            cor = color;
            lChild = nulo;
            rChild = nulo;
        }
        public Object getElement(){
            return element;
        }
    }
    protected No nulo = new No(null, "Negro");
    protected int n = 0;
    protected No raiz;
    @Override
    public String toString(){
        if(isEmpty()) return "arvore vazia";
        inOrder(raiz);
        return "";
    }

    protected void inOrder(No atual){
        if(hasLeft(atual)) inOrder(leftChild(atual));
        System.out.print(atual.element + " ");
        if(hasRight(atual)) inOrder(rightChild(atual));
    }
    public int size(){
        return n;
    }
    public int height(No atual){
        if(isExternal(atual)) return depth(atual);
        int leftHeight = 0, rightHeight = 0;
        if(hasLeft(atual)) leftHeight = height(leftChild(atual));
        if(hasRight(atual)) rightHeight = height(rightChild(atual));
        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public No root(){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        return raiz;
    }
    public No parent(No no){
        if(isRoot(no)) throw new BaseException("Elemento e o raiz nao ha pai");
        return no.parent;
    }
    public No leftChild(No no){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        return no.lChild;
    }
    public No rightChild(No no){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        return no.rChild;
    }
    public boolean hasLeft(No no){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        return no.lChild != null;
    }
    public boolean hasRight(No no){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        return no.rChild != null;
    }
    public boolean isInternal(No no){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        return no.lChild != null || no.rChild != null;
    }
    public boolean isExternal(No no){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        return no.lChild == null && no.rChild == null;
    }
    public boolean isRoot(No no){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
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
        if(isEmpty()) raiz = novo;
        else{
            No pai = find(elem, raiz);
            if(pai.element == elem) throw new BaseException("Elemento ja existe na arvore");
            else{
                if((int)elem < (int)pai.element) pai.lChild = novo;
                else pai.rChild = novo;
                novo.parent = pai;
            }
        }
        ++n;
    }
    public No remove(Object elem){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        No no = find(elem, raiz);
        No retorno = no;
        if(no.element != elem) throw new BaseException("Elemento nao existe na arvore");
        boolean ehRaiz = isRoot(no);
        No pai = null;
        if(!ehRaiz) pai = parent(no);
        if(isExternal(no)){
            if(ehRaiz) raiz = null;
            else{
                No esquerdo = leftChild(pai);
                if(esquerdo == no) pai.lChild = null;
                else pai.rChild = null;
            }
        }
        else if(hasLeft(no) && !hasRight(no) || !hasLeft(no) && hasRight(no)){
            if(hasLeft(no)){
                if(ehRaiz){
                    raiz = leftChild(no);
                    raiz.parent = null;
                }
                else{
                    No filhoEsquerdo = leftChild(no);
                    filhoEsquerdo.parent = pai;
                    pai.lChild = filhoEsquerdo;
                }
            }
            else{
                if(ehRaiz){
                    raiz = rightChild(no);
                    raiz.parent = null;
                }
                else{
                    No filhoDireito = rightChild(no);
                    filhoDireito.parent = pai;
                    pai.rChild = filhoDireito;
                }
            }
        }
        else{
            No substituto = rightChild(no);
            while(hasLeft(substituto)) substituto = leftChild(substituto);
            no.element = substituto.element;
            pai = parent(substituto);
            if(hasRight(substituto)){
                substituto = rightChild(substituto);
                substituto.parent = pai;
                if(leftChild(pai).element == retorno.element) pai.lChild = substituto;
                else pai.rChild = substituto;

            }
            else{
                if(substituto == leftChild(pai)) pai.lChild = null;
                else pai.rChild = null;
            }
        }
        --n;
        return retorno;
    }
}