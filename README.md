# 🚗 Spain Route Finder – Dijkstra’s Algorithm in Java

This project implements a graph-based pathfinding system to determine the shortest routes between cities in Spain using **Dijkstra’s algorithm**. It features fully custom-built data structures (no Java Collections Framework) and a lightweight visual component displaying the cities on a map.

---

## 🧠 Overview

The program models Spanish cities as **nodes** and the roads between them as **edges**, each with an associated distance or cost.  
Using Dijkstra’s algorithm, it finds the most efficient route between any two cities.

Custom data structures such as `MyHashMap`, `MyHashSet`, and `DLList` are implemented to handle graph storage, traversal, and optimization.

---

## ⚙️ Features

- 🗺️ Graph representation of cities in Spain  
- 🧮 Dijkstra’s shortest-path algorithm implementation  
- 🧩 Custom-built data structures (no built-in Java collections)  
- 🖼️ Basic visualization with city icons and map images  
- 🧰 Modular, object-oriented code design  

---

## 🧩 Project Structure

```
Quarter 3/
├── City.class
├── DLList.java
├── Dijkstra.java
├── Graph.java
├── Location.java
├── MyHashMap.java
├── MyHashSet.java
├── Node.java
├── Pair.java
├── Runner.java
├── Screen.java
├── *.png   # Map and city images
└── Instructions.pdf
```

---

## 🚀 Getting Started

### **1. Compile the project**
Make sure you have Java installed (version 8+ recommended).  
In your terminal or IDE:

```bash
javac *.java
```

### **2. Run the program**
```bash
java Runner
```

---

## 🧭 How It Works

1. The program loads predefined city locations and connections.  
2. It builds a graph structure from this data.  
3. When two cities are selected, Dijkstra’s algorithm finds the shortest path.  
4. The path and distances are displayed visually or printed to the console.

---

## 📚 Algorithms and Data Structures

- **Graph Representation:** adjacency list  
- **Shortest Path:** Dijkstra’s algorithm  
- **Custom Classes:**
  - `MyHashMap` – Key-value storage  
  - `MyHashSet` – Unique data storage  
  - `DLList` – Doubly linked list  
  - `Pair` – Lightweight tuple for graph edges  

---

## 🧑‍💻 Author

Developed as part of a **Quarter 3 Java programming project**, focusing on algorithmic problem solving and data structure design.

---

## 📝 License

This project is open for educational and personal use.  
Feel free to modify or extend it with additional algorithms or visual features!

