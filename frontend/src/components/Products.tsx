import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Card } from 'react-bootstrap';

const Products = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        // Fetching products from the API
        axios.get('/products')
            .then(response => {
                console.log('Fetched products:', response.data);
                setProducts(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the products', error);

            });

    }, []);

    return (
        <h1> hi</h1>
        // <div className="product-container">
        //     {products.map((product, index) => (
        //         <Card key={index} style={{ width: '18rem' }}>
        //             <Card.Img variant="top" src={product.imageUrl} />
        //             <Card.Body>
        //                 <Card.Title>{product.name}</Card.Title>
        //                 <Card.Text>{product.description}</Card.Text>
        //             </Card.Body>
        //         </Card>
        //     ))}
        // </div>
    );
};

export default Products;
