//TODO: Implement a products page that is only accessible to authenticated users with the Authority "CAN_RETRIEVE_PRODUCTS"

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Card } from 'react-bootstrap';
import { useAuth } from "../contexts/authenticationcontext/AuthenticationContext";

function Product() {
    const [products, setProducts] = useState([]);
    const { principal } = useAuth();

    useEffect(() => {
        loadProducts();
    }, []);

    const loadProducts = async () => {
        if (userHasPermission()) {
            try {
                const response = await axios.get('/products');
                setProducts(response.data);
            } catch (error) {
                console.error('Fehler beim Laden der Produkte', error);
            }
        }
    };
    // product Cards
    return (
        <div className="product-container">
            {products.map((product, index) => (
                <Card key={index} style={{ width: '18rem' }}>
                    {/*<Card.Img variant="top" src={product.bild} />*/}
                    <Card.Body>
                        {/*<Card.Title>{product.name}</Card.Title>*/}
                        <Card.Text>
                            {/*{product.description}*/}
                        </Card.Text>
                    </Card.Body>
                </Card>
            ))}
        </div>
    );
}

export default Product;

