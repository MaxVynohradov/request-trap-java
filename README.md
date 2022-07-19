## REQUEST TRAP

### Controllers

* TrapsApiController 
  * @GetMapping("/{trapId}/requests") - show list of requests that have been sent to this trap
  * @GetMapping("/{trapId}/**") - consumes GET API requests by trap id, pushes updates to the WS channel
  * @RequestMapping(path = "/{trapId}/**", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}) - consumes API requests with body by trap id, pushes updates to the WS channel
* TrapsUIController
  * @GetMapping("/") - show index page
  * @GetMapping("/{trapId}/requests") - SSR thymeleaf page that 1) show list of requests that have been sent to this trap 2) dynamically updates by WS in realtime


### Entites
* BaseEntity
* RequestData
