import axios from 'axios';

const APIURL = import.meta.env.VITE_APIURL;

export const getAllProducts = async () => {
    try {
        const response = await axios.get(`${APIURL}/getProductos`, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        return response.data;
    } catch (error) {
        console.error("Error al obtener los productos:", error);
        throw error;
    }
};


export const crearProducto = async (producto) => {
  try {
      const response = await axios.post(`${APIURL}/crearProducto`, producto, {
          headers: {
              'Content-Type': 'application/json'
          }
      });
      return response.data;
  } catch (error) {
      console.error("Error al crear el producto:", error);
      throw error;
  }
};

export const asignarProductosLocal = async (payload) => {
  try {
      const response = await axios.post(`${APIURL}/asignarProductosLocal`, payload, {
          headers: {
              'Content-Type': 'application/json'
          }
      });
      return response.data;
  } catch (error) {
      console.error("Error al asignar productos al local:", error);
      throw error;
  }
};
