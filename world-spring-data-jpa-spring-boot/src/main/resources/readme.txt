DAO Pattern:
============
JDBC -> JDK	+ JDBC Driver						
Spring JDBC Template -- modern api --> Spring JDBC Client (Fluent API)
ORM -> JPA -> Jakarta/Java EE -> JDK + JPA Provider (Hibernate/EclipseLink/Open JPA)
              EntityManager/EntityManagerFactory
Spring ORM
------------------------------------------------------------------------------------
Spring Data -> No DAO implementation! 1 Repository Interface
               Naming Convention
	Spring Data JPA    -> Relational DBs
	
	Spring Data Mongo  -> NoSQL: Document/Graph/TimeSeries/KV/Columnar/Vector/...
	Spring Data Redis			              
	Spring Data Neo4j			              
	Spring Data ...	
	
Transaction Management: JTA -> Jakarta/Java EE -> JDK -> Relational DBs
                        JDBC -> Relational DBs 
                        JPA  -> Relational DBs
                        propagation, isolation level -> declarative/annotation
                                                        programmatic
                                                        
Distributed Transaction: TC (Transaction Coordinator) -> Application Server
                         2PC (Two Phase Commit)                                                        
						 XA Driver
                                                        			              