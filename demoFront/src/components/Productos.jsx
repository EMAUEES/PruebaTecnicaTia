import React, { useState, useEffect } from 'react';
import { getAllProducts, crearProducto } from '../services/productosService';

const ProductTable = () => {
  const [tableData, setTableData] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [productForm, setProductForm] = useState({
    nombre: '',
    descripcion: '',
    codigoBarras: '',
    pvp: '',
    fechaCreacion: new Date().toISOString()
  });
  const [showForm, setShowForm] = useState(false);
  const [alertMessage, setAlertMessage] = useState({ type: '', message: '' });

  useEffect(() => {
    loadTableData();
  }, []);

  const loadTableData = async () => {
    try {
      setIsLoading(true);
      const data = await getAllProducts();
      setTableData(data);
    } catch (error) {
      setAlertMessage({ type: 'error', message: 'Error al cargar productos' });
    } finally {
      setIsLoading(false);
    }
  };

  const showAlert = (type, message) => {
    setAlertMessage({ type, message });
    setTimeout(() => setAlertMessage({ type: '', message: '' }), 3000);
  };

  const updateFormField = (e) => {
    setProductForm({ ...productForm, [e.target.name]: e.target.value });
  };

  const resetForm = () => {
    setProductForm({
      nombre: '',
      descripcion: '',
      codigoBarras: '',
      pvp: '',
      fechaCreacion: new Date().toISOString()
    });
    setShowForm(false);
  };

  const submitProduct = async () => {
    if (!productForm.nombre || !productForm.descripcion || !productForm.codigoBarras || !productForm.pvp) {
      showAlert('error', 'Complete todos los campos');
      return;
    }

    try {
      const newProduct = await crearProducto(productForm);
      loadTableData();
      showAlert('success', 'Producto creado exitosamente');
      resetForm();
    } catch (error) {
      showAlert('error', 'Error al crear producto');
    }
  };

  if (isLoading) {
    return (
      <div className="p-6 max-w-6xl mx-auto">
        <div className="bg-white rounded-lg shadow-lg p-8">
          <div className="animate-pulse space-y-4">
            <div className="h-8 bg-gray-200 rounded w-1/4"></div>
            <div className="space-y-3">
              <div className="h-6 bg-gray-200 rounded"></div>
              <div className="h-6 bg-gray-200 rounded"></div>
              <div className="h-6 bg-gray-200 rounded"></div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="p-6 max-w-6xl mx-auto">
      <div className="bg-white rounded-lg shadow-lg">
        <div className="p-6 border-b border-gray-200">
          <div className="flex justify-between items-center">
            <h1 className="text-2xl font-bold text-gray-800">Gestión de Productos</h1>
            <button
              onClick={() => setShowForm(!showForm)}
              className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700"
            >
              {showForm ? 'Cancelar' : 'Nuevo Producto'}
            </button>
          </div>
        </div>

        {alertMessage.message && (
          <div className={`p-4 m-4 rounded-md ${
            alertMessage.type === 'error' ? 'bg-red-100 text-red-700' : 'bg-green-100 text-green-700'
          }`}>
            {alertMessage.message}
          </div>
        )}

        {showForm && (
          <div className="p-6 bg-gray-50 border-b border-gray-200">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <input
                type="text"
                name="nombre"
                placeholder="Nombre del producto"
                value={productForm.nombre}
                onChange={updateFormField}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg"
              />
              <input
                type="text"
                name="descripcion"
                placeholder="Descripción"
                value={productForm.descripcion}
                onChange={updateFormField}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg"
              />
              <input
                type="number"
                name="codigoBarras"
                placeholder="Código de barras"
                value={productForm.codigoBarras}
                onChange={updateFormField}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg"
              />
              <input
                type="number"
                name="pvp"
                placeholder="Precio de venta"
                value={productForm.pvp}
                onChange={updateFormField}
                step="0.01"
                className="w-full px-4 py-2 border border-gray-300 rounded-lg"
              />
            </div>
            <div className="mt-4 flex justify-end gap-2">
              <button
                onClick={resetForm}
                className="px-4 py-2 bg-gray-500 text-white rounded-lg hover:bg-gray-600"
              >
                Cancelar
              </button>
              <button
                onClick={submitProduct}
                className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700"
              >
                Guardar
              </button>
            </div>
          </div>
        )}

        <div className="overflow-x-auto">
          <table className="w-full">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Nombre
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Descripción
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Código
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Precio
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Fecha
                </th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {tableData.map((product) => (
                <tr key={product.idProducto} className="hover:bg-gray-50">
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                    {product.nombre}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                    {product.descripcion}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                    {product.codigoBarras}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                    ${Number(product.pvp).toFixed(2)}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                    {new Date(product.fechaCreacion).toLocaleDateString()}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default ProductTable;