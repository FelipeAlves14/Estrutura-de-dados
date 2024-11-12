package arvores;
public class ArvoreAVL extends ArvoreDePesquisa {
    
    @Override
    protected void inOrder(No atual){
        if(hasLeft(atual)) inOrder(leftChild(atual));
        System.out.print(atual.element + "[" + atual.fb + "]    ");
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
        if(hasLeft(filho)){
            no.rChild = leftChild(filho);
            rightChild(no).parent = no;
        }
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
        if(hasRight(filho)){
            no.lChild = rightChild(filho);
            leftChild(no).parent = no;
        }
        else no.lChild = null;
        filho.rChild = no;
        no.parent = filho;
        no.fb = (--no.fb) - Math.max(filho.fb, 0);
        filho.fb = (--filho.fb) + Math.min(no.fb, 0);
        return filho;
    }

    private void atualizaBalanceamento(No atual){
        No pai = null;
        while(!isRoot(atual)){
            pai = parent(atual);
            if(leftChild(pai) == atual) pai.fb--;
            else if(rightChild(pai) == atual) pai.fb++;
            if(pai.fb == 2){
                if(atual.fb < 0) atual = rotacaoDuplaDireita(pai);
                else atual = rotacaoSimplesDireita(pai);
                if(atual.fb != 0) break;
            }
            else if(pai.fb == -2){
                if(atual.fb > 0) atual = rotacaoDuplaEsquerda(pai);
                else atual = rotacaoSimplesEsquerda(pai);
                if(atual.fb != 0) break;
            }
            else atual = pai;
            if(pai.fb != 0) break;
        }
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
            No atual = novo;
            while(!isRoot(atual)){
                pai = parent(atual);
                if(leftChild(pai) == atual) pai.fb++;
                else if(rightChild(pai) == atual) pai.fb--;
                if(pai.fb == 2){
                    if(atual.fb < 0) atual = rotacaoDuplaDireita(pai);
                    else atual = rotacaoSimplesDireita(pai);
                    if(atual.fb == 0) break;
                }
                else if(pai.fb == -2){
                    if(atual.fb > 0) atual = rotacaoDuplaEsquerda(pai);
                    else atual = rotacaoSimplesEsquerda(pai);
                    if(atual.fb == 0) break;
                }
                else atual = pai;
                if(pai.fb == 0) break;
            }
        }
        ++n;
    }
    
    @Override
    public No remove(Object elem){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        No no = find(elem, raiz);
        if(no.element != elem) throw new BaseException("Elemento nao existe na arvore");
        No retorno = no;
        boolean ehRaiz = isRoot(no);
        No pai = null;
        No atual = retorno;
        if(!ehRaiz) pai = parent(no);
        if(isExternal(no)){
            atualizaBalanceamento(atual);
            if(ehRaiz) raiz = null;
            else{
                No esquerdo = leftChild(pai);
                if(esquerdo == no) pai.lChild = null;
                else pai.rChild = null;
            }
        }
        else if(hasLeft(no) && !hasRight(no) || !hasLeft(no) && hasRight(no)){
            atualizaBalanceamento(atual);
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
            atualizaBalanceamento(substituto);
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