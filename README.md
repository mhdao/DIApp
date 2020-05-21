# A DI's implementation application

## Functionality
 - Support spring bean scopes singleton and prototype
 - Inject dependency via constructor
  
## Class design
 - Inject: an annotation class support to inject dependency, default bean scope is SINGLETON 
 - InjectHandler: to inject dependency and handling bean scope to configured constructor.
 - Mapper: an abstract class storing mapping between interface and implementation class.
 - BeanScope: an Enum class specify bean scope SINGLETON and PROTOTYPE.

## Limitation
Purpose of this application is illustrating how DI works, It doesn't support fully feature of DI functions, only support injection dependency with spring bean scopes singleton and prototype via constructor. 

## How to use
### Inject singleton bean
     @Inject(value = BeanScope.SINGLETON)
     public Singleton(CarService carService) {
        this.carService = carService;
     }
     
     CarService bean should be injected by DI and return the same instance for every time.
### Inject prototype bean
     @Inject(value = BeanScope.PROTOTYPE)
     public Prototype(PlaneService planeService) {
         this.planeService = planeService;
     }
     
     PlaneService bean should be injected by DI and return the different instance for each time
     
### Build and run
    + mvn clean install
    + java -jar DIApp-1.0-SNAPSHOT.jar
     
## Unit test

### Test inject singleton Bean

    @Test
    public void testSingletonBean_should_getSameInstance() throws Exception {
        injectHandler.injectInstance(CarServiceSingleton.class);
        Object carService1 = injectHandler.getInstance(CarService.class);

        injectHandler.injectInstance(CarServiceSingleton.class);
        Object carService2 = injectHandler.getInstance(CarService.class);

        Assertions.assertEquals(carService1, carService2);
    }
### Test inject prototype Bean

    @Test
    public void testPrototypeBean_should_getMultipleInstances() throws Exception {
        injectHandler.injectInstance(PlaneServicePrototype.class);
        Object planeService1 = injectHandler.getInstance(PlaneService.class);

        injectHandler.injectInstance(PlaneServicePrototype.class);
        Object planeService2 = injectHandler.getInstance(PlaneService.class);

        Assertions.assertNotEquals(planeService1, planeService2);
    }

      
    