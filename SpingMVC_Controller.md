### `@RequestMapping` ###
  * can be at:
    * method level
    * class level
      * when you have a controller that deals with a certain subject matter (e.g. an account, a patient, etc.)
### Simplest ###
```
@Controller
public class HomeController {
  @RequestMapping("/",method=RequestMethod.GET,headers="Accept=text/plain")
  public @ResponseBody String home() {
     return "some text here"
  }
}

```