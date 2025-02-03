namespace java com.example.thriftclient.generated

struct Product {
 1: i32 id,
 2: string name,
 3: i32 price
}

service ProductService {
   list <Product> getProduct(),
   Product getProductById(1: i32 prodId),
   void addProduct(1: Product product),
   void updateProduct(1: Product product),
   void deleteProduct(1: i32 prodId)
}