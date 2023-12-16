//TODO: Implement a products page that is only accessible to authenticated users with the Authority "CAN_RETRIEVE_PRODUCTS"

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Card } from 'react-bootstrap';
const [products, setProducts] = useState([]);
const { principal } = useAuth();

const userHasPermission = () => {
    return principal?.authorities.some(
        authority => authority.name === "CAN_RETRIEVE_PRODUCTS"
    );
};
    useEffect(() => {
        loadProducts();
    }, []);

    const loadProducts = async () => {
        if (/* Überprüfe Benutzerberechtigungen */) {
            try {
                const response = await axios.get('/products');
                setProducts(response.data);
            } catch (error) {
                console.error('Fehler beim Laden der Produkte', error);
            }
        }
    };

    return (
        <div className="product-container">
            {products.map((product, index) => (
                <Card key={index} style={{ width: '18rem' }}>
                    <Card.Img variant="top" src={product.imageUrl} />
                    <Card.Body>
                        <Card.Title>{product.name}</Card.Title>
                        <Card.Text>
                            {product.description}
                        </Card.Text>
                    </Card.Body>
                </Card>
            ))}
        </div>
    );
};

export default Product;
