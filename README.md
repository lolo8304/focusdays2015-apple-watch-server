#focus days 2015 Apple Watch server part
contains some REST service to provide cloud access to some data to be used for our App
- deployed on openshift: http://tomcat7-focusdays2015.rhcloud.com/

#REST API 

http://tomcat7-focusdays2015.rhcloud.com/rest/store/objects
- get all objects
- get all 
- example

```json
  [    {    {
      id: "1",
      name: "iPhone 6 Plus",
      brand: "Apple",
      model: "iPhone 6 Plus, 64GB space grey",
      purchase-date: "25.06.2015",
      price-paid: "700",
      price-current: "450",
      currency: "EUR",
      category: "Electonics"
      coverage-test: [
        {
          date: "28.06.2015",
          time: "23:00:02",
          result: [
              {
                location: "at-home",
                risk: "fire",
                covered: "no",
                description: "bla bla bla",
                options: [
                  {
                    title: "standard coverage",
                    price: "45",
                    current: "EUR",
                    description: "add standard risks"
                  },
                  {
                    title: "extended coverage",
                    price: "70",
                    current: "EUR",
                    description: "add all possible fire risks"
                  }
                ]
              }
          ]
        }
      ]
    },
    {
      id: "2",
      name: "Apple Watch",
      price: "670",
      currency: "EUR",
      category: "Electonics"
    }
]
```

http://tomcat7-focusdays2015.rhcloud.com/rest/store/object/<id>
- get object with id = <id>
- including all coverage details
