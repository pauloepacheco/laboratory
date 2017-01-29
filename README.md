# tcc-backend
TCC Backend

This project is a Maven project and it has 3 core modules.<br/>
The 1st module is used for data access. <br/>
The 2nd one is a dedicated module for services. <br/>
The last one is a specific module for Rest Services.<br/>

The Common module is configured as dependency of Service module, so DAO classes can be injected into Service Layer. The same approach is used to combine Service Module into Rest Service Module.
