import React, { useEffect, useState } from 'react';
import axios, {AxiosInstance} from 'axios';
import {Card, Col, ListGroup, Row} from 'react-bootstrap';
import AxiosUtility from "../utility/AxiosUtility";
import Product from '../models/Product';


const Products = () => {
    const [products, setProducts] = useState<Product[]>([]);
    const api: AxiosInstance = AxiosUtility.getApi()
    const authenticate = async () => {
        try {
            const response = await api.get('/products');
            setProducts(response.data);
            console.log(response);
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        authenticate()
    }, [])

    return (
        <div className="product-container">
            <Row xs={1} md={5} className="g-4">
                {products.map((product, index) => (
                    <Col key={index}>
                        <Card style={{ width: '18rem', marginBottom: '20px', marginTop: '20px'}}>
                            <Card.Body>
                                <Card.Body>
                                    <Card.Title>Category: {product.category[0].name}</Card.Title>
                                    <Card.Text>{product.name}</Card.Text>
                                </Card.Body>
                                <ListGroup className="list-group-flush" >
                                    <ListGroup.Item>Origin Country: {product.originCountry.name}</ListGroup.Item>
                                    <ListGroup.Item>Harvest Date: {product.harvestDate}</ListGroup.Item>
                                    <ListGroup.Item>Price: ${product.salePrice} / 100g</ListGroup.Item>
                                </ListGroup>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </div>
    );
};

export default Products;
