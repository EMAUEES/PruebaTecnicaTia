import axios from 'axios';

const APIURL = import.meta.env.VITE_APIURL;

export const getAllTransacciones = async () => {
    try {
        const response = await axios.get(`${APIURL}/getTransacciones`, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        return response.data;
    } catch (error) {
        console.error("Error al obtener las transacciones:", error);
        throw error;
    }
};

export const crearTransaccion = async (transaccion) => {
    try {
        const response = await axios.post(`${APIURL}/ventaProducto`, transaccion, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        return response.data;
    } catch (error) {
        console.error("Error al crear la transaccion:", error);
        throw error;
    }
};