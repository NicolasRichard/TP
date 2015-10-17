package ca.polymtl.log3430;

/**
 * Liste implémentée sous la forme d'une série de noeud.
 * 
 * @author Nicolas Richard
 * @author Adrien Budet
 * @author David Kanaa
 */
public class ListeChainee implements Liste {

	private Node     header;
	private int      size = 0;
	private Node     tail;
	
	@Override
	public void add(int element) {
		// Si la liste est vide, on définit le premier noeud.
        if (size == 0) {
            header = new Node(element);
            size++;
            if (tail == null) {
                tail = header;
            }
        } else { // On ajoute l'élément à la suite de la queue.
            tail.next = new Node(element);
            tail = tail.next;
            size++;
        }
	}

	@Override
	public int getAt(int index) {
		if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Valeur inexistante");
        }
        Node currentNode = header;
        int i = 0;
        // Se déplacer à la position
        while(i != index) {
            i++;
            currentNode = currentNode.next;
        }
        return currentNode.data;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void removeAt(int index) {
		if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Valeur inexistante");
        }
        // S'il s'agit du premier élément, on supprime ce dernier on fait
        // pointer la tête sur le noeud suivant.
        if(index == 0) {
            header = header.next;
            size--;
        } else {
            int i = 0;
            Node currentNode = header;
            Node previousNode = null;
            // Se déplacer à la position
            while(i < index) {
                i++;
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            // Supprimer le noeud à cette position
            previousNode.next = currentNode.next;
            size--;
            if (currentNode == header) {
                header = header.next;
            }
            if (currentNode == tail) {
                tail = previousNode;
            }
        }
	}

	@Override
	public void removeItem(int element) {
		Node currentNode = header;
        Node previousNode = null;
        while (currentNode != null && !(currentNode.data == element)) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        if(currentNode == null) {
            throw new IllegalArgumentException("La valeur n'est pas présente.");
        } else {
            previousNode.next = currentNode.next;
            size--;
        }
        if (currentNode == header) {
            header = header.next;
        }
        if (currentNode == tail) {
            tail = previousNode;
        }
	}

	@Override
	public void reset() {
		size = 0;
        header = null;
        tail = null;
	}

	@Override
	public void setAt(int value, int index) {
		if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Valeur inexistante");
        }
        Node currentNode = header;
        int i = 0;
        // Se déplacer à la position
        while(i < index) {
            i++;
            currentNode = currentNode.next;
        }
        // Modification du contenu du noeud
        currentNode.data = value;
	}
	
    //**************************************************************************
    // Classes imbriquées
    //**************************************************************************

    /**
     * Noeud d'une liste chainée. Contient une valeur de la suite numérique
     * ainsi qu'une référence sur la valeur suivante, si elle existe.
     *
     * @author Chunxia Zhang
     * @author Nicolas Richard
     * @author Adrien Budet
     */
    private static class Node {

        private int  data;
        private Node next;

        /**
         * Constructeur.
         *
         * @param data Valeur de la suite contenue au sein de ce noeud.
         */
        public Node(int data) {
            this.data = data;
        }
    }
}
