interface Category {
    id: string;
    name: string;
}

interface OriginCountry {
    id: string;
    name: string;
}
interface Product {
    id: string;
    name: string;
    category: Category[];
    originCountry: OriginCountry;
    purchasePrice: number;
    salePrice: number;
    harvestDate: string;
}

export default Product;
