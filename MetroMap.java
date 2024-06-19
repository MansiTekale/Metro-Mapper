


import java.util.*;


        
        public class MetroMap {
        
            class Edge {
                String src, dest;
                int weight;
        
                public Edge(String src, String dest, int weight) {
                    this.src = src;
                    this.dest = dest;
                    this.weight = weight;
                }
            }









// CREATING GRAPH

        
        public void Create_Metro_Map(Map<String, List<Edge>> graph) {
                HashSet<String> stations = new HashSet<>();
                stations.add("NOIDA SECTOR 62");
                stations.add("BOTANICAL GARDEN");
                stations.add("YAMUNA BANK");
                stations.add("RAJIV CHOWK");
                stations.add("VAISHALI");
                stations.add("MOTI NAGAR");
                stations.add("JANAK PURI WEST");
                stations.add("DWARKA SECTOR 21");
                stations.add("HUDA CITY CENTER");
                stations.add("SAKET");
                stations.add("VISHWAVIDYALAYA");
                stations.add("CHANDNI CHOWK");
                stations.add("NEW DELHI");
                stations.add("AIIMS");
                stations.add("SHIVAJI STADIUM");
                stations.add("DDS CAMPUS");
                stations.add("IGI AIRPORT");
                stations.add("RAJOURI GARDEN");
                stations.add("NETAJI SUBHASH PLACE");
                stations.add("PUNJABI BAGH WEST");
    
                for (String s : stations) {
                    graph.put(s, new ArrayList<>());
                }
    
                graph.get("NOIDA SECTOR 62").add(new Edge("NOIDA SECTOR 62", "BOTANICAL GARDEN", 8));
                graph.get("BOTANICAL GARDEN").add(new Edge("BOTANICAL GARDEN", "YAMUNA BANK", 10));
                graph.get("YAMUNA BANK").add(new Edge("YAMUNA BANK", "VAISHALI", 8));
                graph.get("YAMUNA BANK").add(new Edge("YAMUNA BANK", "RAJIV CHOWK", 6));
                graph.get("RAJIV CHOWK").add(new Edge("RAJIV CHOWK", "MOTI NAGAR", 9));
                graph.get("MOTI NAGAR").add(new Edge("MOTI NAGAR", "JANAK PURI WEST", 7));
                graph.get("JANAK PURI WEST").add(new Edge("JANAK PURI WEST", "DWARKA SECTOR 21", 6));
                graph.get("HUDA CITY CENTER").add(new Edge("HUDA CITY CENTER", "SAKET", 15));
                graph.get("SAKET").add(new Edge("SAKET", "AIIMS", 6));
                graph.get("AIIMS").add(new Edge("AIIMS", "RAJIV CHOWK", 7));
                graph.get("RAJIV CHOWK").add(new Edge("RAJIV CHOWK", "NEW DELHI", 1));
                graph.get("NEW DELHI").add(new Edge("NEW DELHI", "CHANDNI CHOWK", 2));
                graph.get("CHANDNI CHOWK").add(new Edge("CHANDNI CHOWK", "VISHWAVIDYALAYA", 5));
                graph.get("NEW DELHI").add(new Edge("NEW DELHI", "SHIVAJI STADIUM", 2));
                graph.get("SHIVAJI STADIUM").add(new Edge("SHIVAJI STADIUM", "DDS CAMPUS", 7));
                graph.get("DDS CAMPUS").add(new Edge("DDS CAMPUS", "IGI AIRPORT", 8));
                graph.get("MOTI NAGAR").add(new Edge("MOTI NAGAR", "RAJOURI GARDEN", 2));
                graph.get("PUNJABI BAGH WEST").add(new Edge("PUNJABI BAGH WEST", "RAJOURI GARDEN", 2));
                graph.get("PUNJABI BAGH WEST").add(new Edge("PUNJABI BAGH WEST", "NETAJI SUBHASH PLACE", 3));
    
                
        }
        
            
        
        
        
        
        
        
        
        
        
        
//  DISPLAY STATIONS  (1)
        
        public void displayStations(Map<String, List<Edge>> graph) {
                for (String station : graph.keySet()) {
                    System.out.println("Station: " + station);
                    for (Edge edge : graph.get(station)) {
                        System.out.println("    connects to: " + edge.dest + " with distance " + edge.weight);
                    }
                }
        }
        
            
        
        
        
        
        
        
        
        
        
        
//   DISPLAY MAP (2)
        
        public void display_Map(Map<String, List<Edge>> graph) {
                System.out.println("\t Delhi Metro Map");
                System.out.println("\t------------------");
                System.out.println("----------------------------------------------------\n");
        
                for (String station : graph.keySet()) {
                    String str = station + " =>\n";
                    List<Edge> edges = graph.get(station);
        
                    for (Edge edge : edges) {
                        str += "\t" + edge.dest + "\t";
                        if (edge.dest.length() < 16)
                            str += "\t";
                        if (edge.dest.length() < 8)
                            str += "\t";
                        str += edge.weight + "\n";
                    }
        
                    System.out.println(str);
                }
        
                System.out.println("\t------------------");
                System.out.println("---------------------------------------------------\n");
        }












//RETURN SHORTEST DISTANCE FROM SOURCE TO DESTINATION (3)

        
            public void shortestDistance(Map<String, List<Edge>> graph, String source, String destination) {
                PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
                Map<String, Integer> distances = new HashMap<>();
                Set<String> visited = new HashSet<>();
                boolean pathExists = false; // Flag to track if a path exists
            
                // Initialize distances with infinity
                for (String station : graph.keySet()) {
                    distances.put(station, Integer.MAX_VALUE);
                }
            
                distances.put(source, 0);
                pq.offer(new AbstractMap.SimpleEntry<>(source, 0));
            
                while (!pq.isEmpty()) {
                    Map.Entry<String, Integer> current = pq.poll();
                    String currentStation = current.getKey();
                    int currentDistance = current.getValue();
            
                    if (currentStation.equals(destination)) {
                        // If destination reached, set flag to true
                        pathExists = true;
                        System.out.println("Shortest distance from " + source + " to " + destination + ": " + currentDistance);
                        break; // Exit loop once destination is reached
                    }
            
                    if (visited.contains(currentStation)) continue;
                    visited.add(currentStation);
            
                    for (Edge edge : graph.get(currentStation)) {
                        if (!visited.contains(edge.dest)) {
                            int newDist = currentDistance + edge.weight;
                            if (newDist < distances.get(edge.dest)) {
                                distances.put(edge.dest, newDist);
                                pq.offer(new AbstractMap.SimpleEntry<>(edge.dest, newDist));
                            }
                        }
                    }
                }
            
                if (!pathExists) {
                    System.out.println("No path exists between " + source + " and " + destination);
                }
            }
















// RETURN SHORTEST TIME FROM SOURCE TO DESTINATION(4)


        public static String handleShortestTime(String src, String dst, Map<String, List<Edge>> graph) {
            int min = Integer.MAX_VALUE;
            String ans = "";
            HashMap<String, Boolean> processed = new HashMap<>();
            LinkedList<String> stack = new LinkedList<>();
            HashMap<String, Integer> timeMap = new HashMap<>();


            // Put the source vertex in the stack
            stack.addFirst(src);
            timeMap.put(src, 0);
            
            // While stack is not empty keep on doing the work
            while (!stack.isEmpty()) {
                // Remove a vertex from stack
                String currentVertex = stack.removeFirst();
                int currentTime = timeMap.get(currentVertex);
                
                if (processed.containsKey(currentVertex)) {
                    continue;
                }
                
                // Mark as processed
                processed.put(currentVertex, true);
                
                // If destination vertex is reached
                if (currentVertex.equals(dst)) {
                    if (currentTime < min) {
                        ans = currentVertex;
                        min = currentTime;
                    }
                    continue;
                }
                
                // Process neighbors
                List<Edge> nbrs = graph.get(currentVertex);
                if (nbrs != null) {
                    for (Edge edge : nbrs) {
                        // Process only unprocessed neighbors
                        if (!processed.containsKey(edge.dest)) {
                            // Update time for the neighbor
                            int neighborTime = currentTime + 120 + 40 * edge.weight;
                            // If the neighbor has not been visited yet or the new time is less
                            if (!timeMap.containsKey(edge.dest) || neighborTime < timeMap.get(edge.dest)) {
                                timeMap.put(edge.dest, neighborTime);
                                stack.addFirst(edge.dest);
                            }
                        }
                    }
                }
            }
            
            double minutes = Math.ceil((double) min / 60);
            ans = ans + " " + minutes;
            return ans;
        }















// GET SHORTEST PATH FROM SOURCE TO DESTINATION   (5)

            public static List<String> dijkstra(Map<String, List<Edge>> graph, String source, String destination) {
                // Priority queue to store (node, distance) and sort by distance
                PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
                // Map to store the shortest distance to each node
                Map<String, Integer> distances = new HashMap<>();
                // Map to store the previous node in the optimal path
                Map<String, String> previousNodes = new HashMap<>();
                // Set to track visited nodes
                Set<String> visited = new HashSet<>();
        
                // Initialize distances with infinity
                for (String node : graph.keySet()) {
                    distances.put(node, Integer.MAX_VALUE);
                }
                distances.put(source, 0);
                pq.offer(new AbstractMap.SimpleEntry<>(source, 0));
        
                while (!pq.isEmpty()) {
                    Map.Entry<String, Integer> current = pq.poll();
                    String currentNode = current.getKey();
                    int currentDistance = current.getValue();
        
                    // Skip if node has been visited
                    if (visited.contains(currentNode)) continue;
                    visited.add(currentNode);
                    
        
                    // If destination is reached, construct the path
                    if (currentNode.equals(destination)) {
                        List<String> path = new ArrayList<>();
                        for (String at = destination; at != null; at = previousNodes.get(at)) {
                            path.add(at);
                        }
                        Collections.reverse(path);
                        System.out.println("Shortest distance from " + source + " to " + destination + ": " + currentDistance);
                        return path;
                    }
        
                    // Explore neighbors
                    for (Edge edge : graph.get(currentNode)) {
                        if (!visited.contains(edge.dest)) {
                            int newDist = currentDistance + edge.weight;
                            if (newDist < distances.get(edge.dest)) {
                                distances.put(edge.dest, newDist);
                                previousNodes.put(edge.dest, currentNode);
                                pq.offer(new AbstractMap.SimpleEntry<>(edge.dest, newDist));
                            }
                        }
                    }
                }
        
                // If destination is not reached
                System.out.println("No path exists between " + source + " and " + destination);
                return Collections.emptyList();
            }














// GET SHORTEST TIME PATH  (6)

        public static String handleShortestPath(String src, String dst, Map<String, List<Edge>> graph) {
                int min = Integer.MAX_VALUE;
                String ans = "";
                HashMap<String, Boolean> processed = new HashMap<>();
                LinkedList<String> stack = new LinkedList<>();
                HashMap<String, Integer> timeMap = new HashMap<>();
                HashMap<String, String> previousNodes = new HashMap<>();

                // Put the source vertex in the stack
                stack.addFirst(src);
                timeMap.put(src, 0);

                // While stack is not empty keep on doing the work
                while (!stack.isEmpty()) {
                    // Remove a vertex from stack
                    String currentVertex = stack.removeFirst();
                    int currentTime = timeMap.get(currentVertex);

                    if (processed.containsKey(currentVertex)) {
                        continue;
                    }

                    // Mark as processed
                    processed.put(currentVertex, true);

                    // If destination vertex is reached
                    if (currentVertex.equals(dst)) {
                        if (currentTime < min) {
                            min = currentTime;
                        }
                        continue;
                    }

                    // Process neighbors
                    List<Edge> nbrs = graph.get(currentVertex);
                    if (nbrs != null) {
                        for (Edge edge : nbrs) {
                            // Process only unprocessed neighbors
                            if (!processed.containsKey(edge.dest)) {
                                // Update time for the neighbor
                                int neighborTime = currentTime + 120 + 40 * edge.weight;
                                // If the neighbor has not been visited yet or the new time is less
                                if (!timeMap.containsKey(edge.dest) || neighborTime < timeMap.get(edge.dest)) {
                                    timeMap.put(edge.dest, neighborTime);
                                    stack.addFirst(edge.dest);
                                    previousNodes.put(edge.dest, currentVertex);
                                }
                            }
                        }
                    }
                }

                // Construct the path
                List<String> path = new ArrayList<>();
                String step = dst;
                if (previousNodes.containsKey(step) || step.equals(src)) { // Only if a path exists
                    while (step != null) {
                        path.add(step);
                        step = previousNodes.get(step);
                    }
                }
                Collections.reverse(path);

                // Calculate time in minutes
                double minutes = Math.ceil((double) min / 60);

                // Return the path and the time
                return String.join(" -> ", path) + " in " + minutes + " minutes";
            }


































            
            
        
            public static void main(String[] args) {
                Map<String, List<Edge>> graph = new HashMap<>();
                MetroMap metroMap = new MetroMap();
                metroMap.Create_Metro_Map(graph);
        
                Scanner sc = new Scanner(System.in) ;
                    System.out.println("\n\t\t\t****WELCOME TO THE METRO APP*****");
        
                    while (true) {
                        printMenu();
                        int choice = sc.nextInt();
                        System.out.print("\n***********************************************************\n");
        
                        if (choice == 7) {
                            System.exit(0);
                        }
        
                        handleUserChoice(choice, metroMap, graph, sc);
                    }
                }
            
        

            private static void printMenu() {
                System.out.println("\t\t\t\t~~LIST OF ACTIONS~~\n\n");
                System.out.println("1. LIST ALL THE STATIONS IN THE MAP");
                System.out.println("2. SHOW THE METRO MAP");
                System.out.println("3. GET SHORTEST DISTANCE FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
                System.out.println("4. GET SHORTEST TIME TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
                System.out.println("5. GET SHORTEST PATH (DISTANCE WISE) TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
                System.out.println("6. GET SHORTEST PATH (TIME WISE) TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
                System.out.println("7. EXIT THE MENU");
                System.out.print("\nENTER YOUR CHOICE FROM THE ABOVE LIST (1 to 7) : ");
            }










        
            private static void handleUserChoice(int choice, MetroMap metroMap, Map<String, List<Edge>> graph, Scanner sc) {
                switch (choice) {
                    case 1:
                        metroMap.displayStations(graph);
                        break;
                    case 2:
                        metroMap.display_Map(graph);
                        break;
                    case 3:
                        System.out.print("Enter source station: ");
                        // Consume the newline character after reading the integer choice
                        sc.nextLine();
                        String source = sc.nextLine();
                        System.out.print("Enter destination station: ");
                        String destination = sc.nextLine();
                        metroMap.shortestDistance(graph, source, destination);
                        break;
                    case 4:
                        System.out.print("Enter source station: ");
                        // Consume the newline character after reading the integer choice
                        sc.nextLine();
                        String src = sc.nextLine();
                        System.out.print("Enter destination station: ");
                        String dest = sc.nextLine();
                        System.out.println("shortest time from "+src+" to "+metroMap.handleShortestTime(src,dest,graph) + " mins");
                        break;
                    case 5:
                        System.out.print("Enter source station: ");
                        // Consume the newline character after reading the integer choice
                        sc.nextLine();
                        String sourcee = sc.nextLine();
                        System.out.print("Enter destination station: ");
                        String destinationn = sc.nextLine();
                        List<String> ans = dijkstra(graph,sourcee,destinationn);
                        for(int i=0; i<ans.size(); i++){
                            System.out.println(ans.get(i));
                        }
                        break;
                       
                    case 6:
                        System.out.print("Enter source station: ");
                        // Consume the newline character after reading the integer choice
                        sc.nextLine();
                        String sourceStn = sc.nextLine();
                        System.out.print("Enter destination station: ");
                        String destinationStn = sc.nextLine();
                        String arr = handleShortestPath(sourceStn,destinationStn,graph);
                        System.out.println(arr);
                        break;
                    default:
                        System.out.println("Please enter a valid option!");
                        System.out.println("The options you can choose are from 1 to 7.");
                }
                // Consume the newline character after reading the choice
                sc.nextLine();
            }

    }
    

        