=====> This package contains entity files i.e Database Table related Class Files

=====> In each Class----variable's names had kept as it is in the database 

=====> @Column(name="xxxxx") --->name attribute represents the actualy column in the database.

=====> if there is no @Column attribute then variable name must be same as database field name

=====> For status fields----It should be EnumType and the corresponding Enum Classes will be in com.ramersoft.pos.enum packages  

=====> import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType; to work with enum dataTypes
  
Note:
Important:
********************************
 whatever the entity class You defined in the package it should be included in the dispatcher servlet...
 Otherwise hibernate won't consider it for mapping the result 

