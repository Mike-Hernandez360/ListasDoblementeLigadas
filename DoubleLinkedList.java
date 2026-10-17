public class DoubleLinkedList {
    private Node head;   // Primer nodo de la lista doblemente enlazada
    private Node tail;   // Último nodo de la lista doblemente enlazada
    private int length;  // Cantidad total de elementos en la lista

    // Clase interna que representa cada nodo individual de la lista
    public class Node{
        public int value; // Valor numérico almacenado en el nodo
        public Node next; // Puntero que apunta al siguiente nodo
        public Node prev; // Puntero que apunta al nodo anterior

        // Constructor del nodo
        public Node(int value){
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    // Constructor principal de la lista doblemente enlazada: inicializa con un primer nodo
    public DoubleLinkedList(int value){
        Node newNode = new Node(value);
        length = 1;
        head = newNode;
        tail = newNode;
    }

    // Imprime en consola el valor del nodo inicial (Head)
    public void getHead(){
        if(head != null)System.out.println("Head: " + head.value);
        else System.out.println("Head: null");
    }

    // Imprime en consola el valor del nodo final (Tail)
    public void getTail(){
        if (tail != null)System.out.println("Tail: " + tail.value);
        else System.out.println("Tail: null");
    }

    // Imprime en consola la longitud actual de la lista
    public void getLength(){System.out.println("Length: " + length);}

    // Agrega un nuevo nodo con el valor especificado al final de la lista
    public void append(int value){
        Node newNode = new Node(value);
        if(length == 0){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }
    
    // Recorre y muestra en consola todos los valores de los nodos que conforman la lista
    public void printList(){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    // Elimina y retorna el último nodo de la lista
    public Node removeLast(){
        if(length == 0) return null;
        Node temp = tail;
        if(length == 1){
            head = null;
            tail = null;
        }else{
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    // Elimina y retorna el primer nodo de la lista
    public Node removeFirst(){
        if(length == 0) return null;
        Node temp = head;
        if(length == 1){
            head = null;
            tail = null;
        }else{
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        length--;
        return temp;
    }

    // Busca y retorna el nodo ubicado en un índice específico de forma optimizada
    public Node get(int index){
        if(index < 0 || index >= length) return null;
        Node temp = head;
        // Optimización: buscar desde el head o desde el tail dependiendo de la cercanía
        if(index < length / 2){
            for (int i = 0; i < index; i++)temp = temp.next;
        }else{
            temp = tail;
            for (int i = length - 1; i > index; i--)temp = temp.prev;
        }
        return temp;
    }

    // Actualiza el valor de un nodo en un índice específico. Retorna true si tiene éxito.
    public boolean set(int index, int value) {
        Node temp = get(index);
        if(temp != null){
            temp.value = value;
            return true;
        }
        return false;
    }

    // Agrega un nuevo nodo con el valor dado al principio de la lista
    public boolean prepend(int value){
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
        return true;
    }

    // Inserta un nuevo nodo con el valor dado en una posición (índice) arbitraria
    public boolean insert(int index, int value){
        if(index < 0 || index > length) return false;
        if(index == 0) return prepend(value);
        if(index == length) return append(value);

        Node newNode = new Node(value);
        Node before = get(index - 1);
        Node after = before.next;

        newNode.prev = before;
        newNode.next = after;
        before.next = newNode;
        after.prev = newNode;
        length++;
        return true;
    }

    // Elimina el nodo ubicado en un índice específico y lo retorna
    public Node remove(int index){
        if(index < 0 || index >= length) return null;
        if(index == 0) return removeFirst();
        if(index == length - 1) return removeLast();

        Node temp = get(index);
        
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        temp.next = null;
        temp.prev = null;
        
        length--;
        return temp;
    }

    // Encuentra y retorna el nodo del medio usando dos punteros
    public Node nodeHalf(){
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Invierte por completo el orden de los elementos y enlaces de la lista doble
    public void reverse(){
        Node current = head;
        Node temp = null;
        tail = head; // El antiguo head será el nuevo tail
        while(current != null){
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev; // Avanzamos usando el nuevo enlace previo (que antes era next)
        }
        if (temp != null)head = temp.prev; // El nuevo head se actualiza correctamente
    }

    // Detecta si la lista contiene algún ciclo (en listas doblemente ligadas puras lineales normales devuelve falso)
    public boolean isCyclic(){
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
}