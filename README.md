# The DI's implementation application

## Functionality
 - Support spring bean scopes singleton and prototype
 - Inject dependency via constructor, field and method
  
## Class design
 - Inject: an annotation class support to inject dependency, default bean scope is SINGLETON 
 - InjectHandler: to inject dependency and handling bean scope to configured constructor.
 - Mapper: an abstract class storing mapping between interface and implementation class.
 - BeanScope: an Enum class specify bean scope SINGLETON and PROTOTYPE.

## Limitation
Purpose of this application is illustrating how DI works, It doesn't support fully feature of DI functions, support injection dependency with spring bean scopes singleton and prototype via constructor, field and method. 

## How to use
### Inject singleton bean via constructor
    @Inject(value = BeanScope.SINGLETON)
    public CarServiceSingleton(CarService carService) {
        this.carService = carService;
    }
     
    CarService bean should be injected by DI and return the same instance for every time.
    
### Inject singleton bean via field
	 @Inject
	 private PlaneService planeService;
     
    CarService bean should be injected by DI and return the same instance for every time.
    
### Inject singleton bean via method
    @Inject
    public void setPlaneService(PlaneService planeService) {
        this.planeService = planeService;
    }
	
### Inject prototype bean via constructor
    @Inject(value = BeanScope.PROTOTYPE)
    public PlaneServicePrototype(PlaneService planeService) {
        this.planeService = planeService;
    }
    PlaneService bean should be injected by DI and return the different instance for each time
    
### Inject prototype bean via field
    @Inject(value = BeanScope.PROTOTYPE)
    private PlaneService planeService;
     
    PlaneService bean should be injected by DI and return the different instance for each time
    
### Inject prototype bean via method
    @Inject(value = BeanScope.PROTOTYPE)
    public void setPlaneService(PlaneService planeService) {
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
		injectHandler.addMapper(mapperTest);
		injectHandler.injectInstance(CarServiceSingleton.class);
		Object carService1 = injectHandler.getInjectionInstance(CarService.class);

		injectHandler.injectInstance(CarServiceSingleton.class);
		Object carService2 = injectHandler.getInjectionInstance(CarService.class);

		Assertions.assertEquals(carService1, carService2);
	}
### Test inject prototype Bean

	@Test
	public void testPrototypeBean_should_getMultipleInstances() throws Exception {
		injectHandler.addMapper(mapperTest);
		injectHandler.injectInstance(PlaneServicePrototype.class);
		Object planeService1 = injectHandler.getInjectionInstance(PlaneService.class);

		injectHandler.injectInstance(PlaneServicePrototype.class);
		Object planeService2 = injectHandler.getInjectionInstance(PlaneService.class);

		Assertions.assertNotEquals(planeService1, planeService2);
	}

      
    