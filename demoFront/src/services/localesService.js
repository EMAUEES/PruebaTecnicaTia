import axios from 'axios';

const APIURL = import.meta.env.VITE_APIURL;

export const getLocales = async () => {
    try {
      const response = await axios.get(`${APIURL}/getlocales`, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
      return response.data;
    } catch (error) {
      console.error("Error al obtener los locales:", error);
      throw error;
    }
  };


export const crearLocal = async (local) => {
    try {
        const response = await axios.post(`${APIURL}/crearLocal`, [local], {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        return response.data;
    } catch (error) {
        console.error("Error al crear el local:", error);
        throw error;
    }
};

export const getProductosDelLocal = async (idlocal) => {
    try {
        const response = await axios.get(`${APIURL}/getProductosDeLocal/${idlocal}`, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        return response.data;
    } catch (error) {
        console.error("Error al obtener los productos del local:", error);
        throw error;
    }
};