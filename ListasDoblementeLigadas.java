// Editor: Miguel Angel Hernandez Martinez

public class ListasDoblementeLigadas {
    public static void main(String[] args) {
        // --- Creación de la lista e inserción al final (append) ---
        System.out.println("=== Creación de la lista y método append ===");
        // Creamos una nueva lista doblemente enlazada con el valor inicial 10.
        DoubleLinkedList list = new DoubleLinkedList(10);
        
        // Agregamos elementos al final de la lista de manera consecutiva (20, 30, 40, 50).
        // Cada nodo nuevo actualiza correctamente los punteros 'next' y 'prev'.
        list.append(20);
        list.append(30);
        list.append(40);
        list.append(50);
        
        System.out.println("Elementos actuales de la lista:");
        list.printList(); // Imprime: 10, 20, 30, 40, 50

        // Mostramos el estado actual de los metadatos de la lista.
        System.out.println("\n--- Metadatos de la lista ---");
        list.getHead();   // Muestra Head: 10
        list.getTail();   // Muestra Tail: 50
        list.getLength(); // Muestra Length: 5

        // --- Inserción al inicio (prepend) y en un índice específico (insert) ---
        System.out.println("\n=== Métodos prepend e insert ===");
        
        // Agregamos un nodo con valor 5 al principio de la lista.
        // Actualiza el puntero 'prev' del antiguo head y el nuevo 'head'.
        list.prepend(5);
        System.out.println("Lista después de prepend(5):");
        list.printList(); // Imprime: 5, 10, 20, 30, 40, 50

        // Insertamos un nodo con valor 25 exactamente en el índice 3.
        // Utiliza la búsqueda optimizada para encontrar el nodo anterior y reacomodar ambos punteros.
        list.insert(3, 25);
        System.out.println("Lista después de insert(3, 25):");
        list.printList(); // Imprime: 5, 10, 20, 25, 30, 40, 50


        // --- Obtener (get) y Modificar (set) valores por índice ---
        System.out.println("\n=== Métodos get y set ===");
        
        // Obtenemos el nodo ubicado en el índice 2 (que corresponde al valor 20).
        // La lista doble optimiza la búsqueda recorriendo desde el head o el tail según la cercanía.
        DoubleLinkedList.Node nodeAtIndex2 = list.get(2);
        if(nodeAtIndex2 != null) System.out.println("Valor encontrado en el índice 2: " + nodeAtIndex2.value);

        // Modificamos el valor del nodo en el índice 2, cambiándolo de 20 a 99.
        list.set(2, 99);
        System.out.println("Lista después de actualizar el índice 2 a 99 con set(2, 99):");
        list.printList();

        // --- Encontrar el nodo central (nodeHalf) ---
        System.out.println("\n=== Encontrar el punto medio con nodeHalf ===");
        
        // Utiliza la técnica de dos punteros ('slow' avanza 1 paso y 'fast' avanza 2 pasos)
        // para encontrar el nodo de la mitad de la lista doble.
        DoubleLinkedList.Node middleNode = list.nodeHalf();
        if(middleNode != null) System.out.println("El nodo central/mitad de la lista es: " + middleNode.value);

        // --- Invertir la lista (reverse) ---
        System.out.println("\n=== Invertir la lista con reverse ===");
        
        // Invierte la dirección de todos los punteros 'next' y 'prev' en la lista doble,
        // intercambiando de manera correcta el 'head' con el 'tail'.
        list.reverse();
        System.out.println("Lista después de invertir su orden:");
        list.printList();

        // --- Eliminación de elementos (removeFirst, removeLast, remove por índice) ---
        System.out.println("\n=== Pruebas de eliminación (Remove) ===");
        
        // Eliminamos el primer elemento de la lista actual ajustando los enlaces del nuevo head.
        DoubleLinkedList.Node removedFirst = list.removeFirst();
        System.out.println("Elemento removido del inicio: " + (removedFirst != null ? removedFirst.value : "null"));

        // Eliminamos el último elemento de la lista actual actualizando el tail y sus referencias.
        DoubleLinkedList.Node removedLast = list.removeLast();
        System.out.println("Elemento removido del final: " + (removedLast != null ? removedLast.value : "null"));

        // Eliminamos el elemento ubicado específicamente en el índice 1 reconectando los nodos adyacentes.
        DoubleLinkedList.Node removedAtIndex = list.remove(1);
        System.out.println("Elemento removido en el índice 1: " + (removedAtIndex != null ? removedAtIndex.value : "null"));

        System.out.println("Lista resultante tras las eliminaciones:");
        list.printList();

        // --- Detección de ciclos (isCyclic) ---
        System.out.println("\n=== Verificación de ciclos con isCyclic ===");
        
        // Comprueba si existe algún ciclo (bucle infinito donde un nodo apunta a uno anterior).
        // Para esta lista doble lineal normal, debe retornar falso.
        boolean hasCycle = list.isCyclic();
        System.out.println("¿La lista actual contiene un ciclo?: " + hasCycle);
    }
}