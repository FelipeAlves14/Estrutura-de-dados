public class ArvoreAVL extends ArvoreDePesquisa {
    
    @Override
    protected void inOrder(No atual){
        if(hasLeft(atual)) inOrder(leftChild(atual));
        System.out.print(atual.element + "[" + atual.fb + "]");
        if(hasRight(atual)) inOrder(rightChild(atual));
    }

    private No rotacaoSimplesEsquerda(No no){
        No filho = rightChild(no);
        if(!isRoot(no)){
            No pai = parent(no);
            filho.parent = pai;
            if(leftChild(pai) == no) pai.lChild = filho;
            else pai.rChild = filho;
        }
        else{
            filho.parent = null;
            raiz = filho;
        }
        if(hasLeft(filho)) no.rChild = leftChild(filho);
        else no.rChild = null;
        filho.lChild = no;
        no.parent = filho;
        no.fb = (++no.fb) - Math.min(filho.fb, 0);
        filho.fb = (++filho.fb) + Math.max(no.fb, 0);
        return filho;
    }
    
    private No rotacaoSimplesDireita(No no){
        No filho = leftChild(no);
        if(!isRoot(no)){
            No pai = parent(no);
            filho.parent = pai;
            if(leftChild(pai) == no) pai.lChild = filho;
            else pai.rChild = filho;
        }
        else{
            filho.parent = null;
            raiz = filho;
        }
        if(hasRight(filho)) no.lChild = rightChild(filho);
        else no.lChild = null;
        filho.rChild = no;
        no.parent = filho;
        no.fb = (--no.fb) - Math.max(filho.fb, 0);
        filho.fb = (--filho.fb) + Math.min(no.fb, 0);
        return filho;
    }

    private No rotacaoDuplaEsquerda(No no){
        rotacaoSimplesDireita(rightChild(no));
        return rotacaoSimplesEsquerda(no);
    }

    private No rotacaoDuplaDireita(No no){
        rotacaoSimplesEsquerda(leftChild(no));
        return rotacaoSimplesDireita(no);
    }

    @Override
    public int height(No atual){
        if(isExternal(atual)) return depth(atual);
        int leftHeight = 0, rightHeight = 0;
        if(hasLeft(atual)) leftHeight = height(leftChild(atual));
        if(hasRight(atual)) rightHeight = height(rightChild(atual));
        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }

    @Override
    public void insert(Object elem){
        No novo = new No(elem);
        if(isEmpty()) raiz = novo;
        else{
            No pai = find(elem, raiz);
            if(pai.element == elem) throw new BaseException("Elemento ja existe na Árvore");
            else{
                if((int)elem < (int)pai.element) pai.lChild = novo;
                else pai.rChild = novo;
                novo.parent = pai;
            }
            No atual = novo;
            while(!isRoot(atual)){
                pai = parent(atual);
                if(leftChild(pai) == atual) pai.fb++;
                else if(rightChild(pai) == atual) pai.fb--;
                if(pai.fb == 0) break;
                if(pai.fb == 2){
                    if(atual.fb < 0) atual = rotacaoDuplaDireita(pai);
                    else atual = rotacaoSimplesDireita(pai);
                }
                else if(pai.fb == -2){
                    if(atual.fb > 0) atual = rotacaoDuplaEsquerda(pai);
                    else atual = rotacaoSimplesEsquerda(pai);
                }
                else atual = pai;
            }
        }
        ++n;
    }
}