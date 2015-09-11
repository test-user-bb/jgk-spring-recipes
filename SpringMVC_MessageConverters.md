## Out of the box Message Converters ##
  * ` StringHttpMessageConverter `
  * ` FormHttpMessageConverter `
  * ` ByteArrayMessageConverter `
  * ` Jaxb2RootElementHttpMessageConverter `
  * ` MappingJacksonHttpMessageConverter `
  * ` SourceHttpMessageConverter `
  * ` ResourceHttpMessageConverter `
  * ` AtomFeedHttpMessageConverter `
  * ` RssChannelHttpMessageConverter `
  * ` BufferedImageHttpMessageConverter `
  * ` MarshallingHttpMessageConverter `

## Custom ##
  * You can register your own or customize the ones provided by setting the **messageConverters** property of the ` AnnotationMethodHandlerAdapter ` bean.