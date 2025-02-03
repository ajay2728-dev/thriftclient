package com.example.thriftclient.service;

import com.example.thriftclient.generated.Product;
import com.example.thriftclient.generated.ProductService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.layered.TFramedTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
public class ProductClientService {
    private static final Logger log = LoggerFactory.getLogger(ProductClientService.class);
    private final ProductService.Client client;

    public ProductClientService(){
        try {
            log.info("thrift  client ....");
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            client = new ProductService.Client(protocol);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Thrift client", e);
        }
    }

    public List<Product> getProduct(){
        try {
          return client.getProduct();
        } catch (RuntimeException | TException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getProductById(int prodId){
        try {
            return client.getProductById(prodId);
        }  catch (TException e) {
            throw new RuntimeException(e);
        }
    }

    public void addProduct(Product product){
        try {
            log.info("client service adding product..");
            client.addProduct(product);
        } catch (TException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct( Product product){
        try {
            client.updateProduct(product);
        } catch (TException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct( int prodId) throws TException {
        try {
            client.deleteProduct(prodId);
        } catch (TException e){
            throw new RuntimeException(e);
        }
    }


}
