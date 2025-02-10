import React, { useState, useEffect } from 'react';
import { getLocales, getProductosDelLocal } from '../services/localesService';
import { getAllProducts, asignarProductosLocal } from '../services/productosService';

const ProductosPorLocal = () => {
  const [locales, setLocales] = useState([]);
  const [selectedLocal, setSelectedLocal] = useState('');
  const [productos, setProductos] = useState([]);
  const [isLoadingProductos, setIsLoadingProductos] = useState(false);
  const [isLoadingLocales, setIsLoadingLocales] = useState(true);
  const [alert, setAlert] = useState({ type: '', message: '' });

  const [showAddForm, setShowAddForm] = useState(false);
  const [availableProducts, setAvailableProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState('');
  const [newStock, setNewStock] = useState('');

  useEffect(() => {
    const loadLocales = async () => {
      try {
        setIsLoadingLocales(true);
        const data = await getLocales();
        setLocales(data);
      } catch (error) {
        setAlert({ type: 'error', message: 'Error al cargar locales' });
      } finally {
        setIsLoadingLocales(false);
      }
    };
    loadLocales();
  }, []);

  useEffect(() => {
    if (selectedLocal) {
      const loadProductos = async () => {
        try {
          setIsLoadingProductos(true);
          const data = await getProductosDelLocal(selectedLocal);
          setProductos(data);
        } catch (error) {
          setAlert({ type: 'error', message: 'Error al cargar productos' });
        } finally {
          setIsLoadingProductos(false);
        }
      };
      loadProductos();
    }
  }, [selectedLocal]);

  const handleLocalChange = (e) => {
    setSelectedLocal(e.target.value);
    setProductos([]);
    setShowAddForm(false);
  };

  const handleShowAddForm = async () => {
    try {
      const data = await getAllProducts();
      setAvailableProducts(data);
      setShowAddForm(true);
    } catch (error) {
      setAlert({ type: 'error', message: 'Error al cargar productos disponibles' });
    }
  };

  const handleSaveAssignment = async () => {
    if (!selectedProduct || !newStock) {
      setAlert({ type: 'error', message: 'Seleccione un producto y especifique el stock' });
      return;
    }

    const payload = {
      idLocal: parseInt(selectedLocal, 10),
      productos: [
        {
          idProducto: parseInt(selectedProduct, 10),
          stock: parseInt(newStock, 10)
        }
      ]
    };

    try {
      await asignarProductosLocal(payload);
      setAlert({ type: 'success', message: 'Producto asignado correctamente' });
      setShowAddForm(false);
      const updatedProducts = await getProductosDelLocal(selectedLocal);
      setProductos(updatedProducts);
      setSelectedProduct('');
      setNewStock('');
    } catch (error) {
      setAlert({ type: 'error', message: 'Error al asignar el producto' });
    }
  };

  const handleCancelAssignment = () => {
    setShowAddForm(false);
    setSelectedProduct('');
    setNewStock('');
  };

  return (
    <div className="p-6 max-w-6xl mx-auto">
      <div className="bg-white rounded-lg shadow-lg p-6">
        <h1 className="text-2xl font-bold text-gray-800 mb-4">Productos por Local</h1>
        {alert.message && (
          <div className={`p-4 mb-4 rounded-md ${alert.type === 'error' ? 'bg-red-100 text-red-700' : 'bg-green-100 text-green-700'}`}>
            {alert.message}
          </div>
        )}
        {isLoadingLocales ? (
          <div className="animate-pulse">
            <div className="h-8 bg-gray-200 rounded w-1/3 mb-4"></div>
          </div>
        ) : (
          <select
            value={selectedLocal}
            onChange={handleLocalChange}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg mb-4 bg-white text-black"
          >
            <option value="">Seleccione un local</option>
            {locales.map((local) => (
              <option key={local.idLocal} value={local.idLocal}>
                {local.nombre}
              </option>
            ))}
          </select>
        )}

        {isLoadingProductos ? (
          <div className="animate-pulse">
            <div className="h-6 bg-gray-200 rounded w-full mb-2"></div>
            <div className="h-6 bg-gray-200 rounded w-full mb-2"></div>
            <div className="h-6 bg-gray-200 rounded w-full mb-2"></div>
          </div>
        ) : (
          selectedLocal && (
            <>
              {productos.length > 0 ? (
                <div className="overflow-x-auto">
                  <table className="w-full">
                    <thead className="bg-gray-50">
                      <tr>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nombre</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Descripción</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Código de Barras</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">PVP</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Stock</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-gray-200">
                      {productos.map((producto) => (
                        <tr key={producto.idProducto} className="hover:bg-gray-50">
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{producto.idProducto}</td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{producto.nombre}</td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{producto.descripcion}</td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{producto.codigoBarras}</td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{producto.pvp}</td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{producto.stock}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              ) : (
                <p className="text-gray-600">No hay productos disponibles para este local.</p>
              )}
            </>
          )
        )}

        {selectedLocal && !showAddForm && (
          <button
            onClick={handleShowAddForm}
            className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Agregar Producto al Local
          </button>
        )}

        {showAddForm && (
          <div className="mt-4 p-4 border rounded">
            <h2 className="text-lg font-semibold mb-2">Asignar Producto</h2>
            <div className="mb-4">
              <label className="block mb-1">Producto</label>
              <select
                value={selectedProduct}
                onChange={(e) => setSelectedProduct(e.target.value)}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg bg-white text-black"
              >
                <option value="">Seleccione un producto</option>
                {availableProducts.map((prod) => (
                  <option key={prod.idProducto} value={prod.idProducto}>
                    {prod.nombre}
                  </option>
                ))}
              </select>
            </div>
            <div className="mb-4">
              <label className="block mb-1">Stock</label>
              <input
                type="number"
                value={newStock}
                onChange={(e) => setNewStock(e.target.value)}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg"
                placeholder="Ingrese el stock"
              />
            </div>
            <div className="flex space-x-2">
              <button
                onClick={handleSaveAssignment}
                className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600"
              >
                Guardar
              </button>
              <button
                onClick={handleCancelAssignment}
                className="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600"
              >
                Cancelar
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductosPorLocal;
