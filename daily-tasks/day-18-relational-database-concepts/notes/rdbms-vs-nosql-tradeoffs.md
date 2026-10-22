# RDBMS vs NoSQL: Architectural Trade-Offs

## 1. Comparison Matrix
| Architectural Aspect | Relational Database (RDBMS) | NoSQL Database |
| :--- | :--- | :--- |
| **Data Model** | Tables, Rows, Strict Schemas (Schema-on-Write) | Documents (JSON), Key-Value, Columnar, Graph |
| **ACID vs BASE** | **ACID** (Atomicity, Consistency, Isolation, Durability) | **BASE** (Basically Available, Soft state, Eventual consistency) |
| **Scalability** | Vertical (Scale-Up), read replicas | Horizontal (Scale-Out / Sharding across nodes) |
| **Joins & Relations** | Native SQL `JOIN` across foreign keys | Embedded denormalized data or application-side lookups |
| **Examples** | PostgreSQL, MySQL, Oracle, MariaDB | MongoDB, Redis, Apache Cassandra, Neo4j |

---

## 2. When to Choose RDBMS
- High financial accuracy and auditability (Banking, Invoicing, ERP).
- Complex relationships with multi-table joins (e.g., College Registrations, Supply Chain).
- Read-heavy and write-heavy workloads where data redundancy must be strictly avoided.

---

## 3. When to Choose NoSQL
- Massive write volume with unstructured or rapidly evolving schemas (IoT sensor data, social media feeds).
- High-speed distributed caching (Redis).
- Real-time chat messages or shopping cart sessions.
