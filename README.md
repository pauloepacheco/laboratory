# tcc-backend
TCC Backend

This project is based on Maven and it has 3 core modules.
The first one for data access, the second one is a dedicated module for services and the last one is a specific module for Rest Services.
Common module is configured as dependency of Service module, so DAO classes can be injected into Service Layer. The same approach is used to combine Service Module into Rest Service Module.
