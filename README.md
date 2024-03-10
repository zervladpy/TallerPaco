# Taller Paco

## Descripción

```
Pequeño proyecto hecho con java.

 - Incluye uso de JavaFX
 - Uso de Hibernate
 - Uso de JakartaPersistence
 - Uso de H2 Database
 - Uso de Lombok
```

## ER

![ER](images/er.png)

## Entidades

````
 Todas las entidades **DEBEN** implementar ITEntity 

 - Client
 - Car     - includes Embeded - CarDetails
 - Invoice - includes Embeded PK - InvoicePK
 - Service
 - Brand   - inclides Lob as logo
````

## DAO

```
 - Incluye DAO para todas las entidades
 
 - ITEntity                                     ** Interface **
 - IDAO<T extends ITEntity>                     ** Interface **
 
 - DAO<T extends ITEntity> implements IDAO<T>   ** Clase Base de DAO **
 
 - ClientDAO extends DAO<Client>                
 - CarDAO extends DAO<Car>
 - InvoiceDAO extends DAO<Invoice>
 - ServiceDAO extends DAO<Service>
 - BrandDAO extends DAO<Brand>
    
```

## Aplicación

```
 - Incluye CRUD para todas las entidades
 - Incluye querys personalizadas
 - Incluye uso de DTOS
```

### Imágenes

- **brand-view**
![brand-view](images/brand-view-no-selection.png)
![brand-view](images/brand-view-selection.png)
![brand-view](images/brand-view-invalid-form.png)

- **car-view**
![car-view](images/car-view-no-selection.png)
![car-view](images/car-view-selection.png)
![car-view](images/car-view-invalid-form.png)

- **client-view**
![client-view](images/client-view-no-selection.png)
![client-view](images/client-view-selection.png)
![client-view](images/client-view-invalid-form.png)

- **invoice-view**
![invoice-view](images/invoice-view-no-selection.png)
![invoice-view](images/invoice-view-selection.png)
![invoice-view](images/invoice-view-add-services-to-invoice.png)
![invoice-view](images/invoice-view-no-pk-edit-error.png)

- **service-view**
![service-view](images/services-view-no-selection.png)
![service-view](images/services-view-selection.png)
