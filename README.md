# VendingMachine
Basic concurrent vending machine developed in Java

### Assumptions
1. Machine supports one customer purchase at a time 
2. Supplier can change the machine state, if no other customer purchase is in progress

### Application Flow
```
                                                                                   
                                 __________________        ________________        _____________    
                                |                  |      |                |      |             |
                                | No coin inserted |--->  | Coin inserted  | ---> |  Dispensing |
                                |__________________|      |________________|      |_____________|                                                                       |                  |
                                                                  
                                                                  ^                    
                                                                  |                      
                                                                  |                 _____________________      
    ___________________          _________________         ________________        |                     |
   |                   |        |                 |       |                |       | Calculate change /  |                              
   | Supplier/Consumer |  --->  | Vending Machine | --->  | Fetch State    | --->  | Create Bill         | 
   |___________________|        |_________________|       |________________|       | Change product      |
                                                                                   |_____________________|
                                                                                   
                                            
```

### Design Considerations

1. VendingMachine - Interface which abstracts out the necessary methods for any vending machine
2. SupplierDemands - Interface which outlines supplier demands
3. State - Interface which contains different states of an vending machine
4. ConcurrentVendingMachine - Concrete implementation of vending machine support both supplier and customer demands
5. Enum for coins, CustomExceptions for throwing necessary errors

### Design Patterns

* Factory - to encapsulate creation logic of VendingMachine
* State - to maintain different states of an VendingMachine
* Adapter - is used to create Inventory by wrapping java.util.Map
* Singleton - to ensure one vending machine is used for both customer and supplier 

### Limitation

1. Coin change - Even though machine has enough coins, sometimes it might say insufficient funds
