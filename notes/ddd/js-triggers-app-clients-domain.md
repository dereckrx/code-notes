====================== DDD JS notes??? =====

Triggers -> use-cases -> clients -> domain
Action -> 

Triggers / Interfaces
* UI clicks -> trigger actions
* Web sockets -> trigger actions
* return type of triggers is HTML (generated by React)

Domain: data
* data from backend 

AppDomain / Use cases
* action creators (app logic) and reducers (app state)
* presentation state
* presentation logic 

presentation state and logic handled by modelView object, 
thats then passed into react for rendering

Clients/Infra
* navigation actions
* State management 

Login submit actions
-> component button calls 