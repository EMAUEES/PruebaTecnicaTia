import React, { useState, useEffect } from 'react';
import { getAllTransacciones, crearTransaccion } from '../services/transaccionesService';
import { getLocales, getProductosDelLocal } from '../services/localesService';

const Transacciones = () => {
  const [transacciones, setTransacciones] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [alert, setAlert] = useState({ type: '', message: '' });
  const [selectedTransaction, setSelectedTransaction] = useState(null);
  const [showCreateForm, setShowCreateForm] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const [availableLocals, setAvailableLocals] = useState([]);
  const [selectedLocalForCreation, setSelectedLocalForCreation] = useState('');
  const [transactionType, setTransactionType] = useState('');
  const [products, setProducts] = useState([{ idProducto: '', cantidad: '', iva: '' }]);
  const [availableProducts, setAvailableProducts] = useState([]);

  useEffect(() => {
    const loadTransacciones = async () => {
      try {
        setIsLoading(true);
        const data = await getAllTransacciones();
        setTransacciones(data);
      } catch (error) {
        setAlert({ type: 'error', message: 'Error al cargar las transacciones' });
      } finally {
        setIsLoading(false);
      }
    };

    loadTransacciones();
  }, []);

  useEffect(() => {
    const loadLocals = async () => {
      try {
        const localsData = await getLocales();
        setAvailableLocals(localsData);
      } catch (error) {
        setAlert({ type: 'error', message: 'Error al cargar los locales.' });
      }
    };

    loadLocals();
  }, []);

  useEffect(() => {
    const loadProducts = async () => {
      if (selectedLocalForCreation) {
        try {
          const productsData = await getProductosDelLocal(selectedLocalForCreation);
          setAvailableProducts(productsData);
          setProducts([{ idProducto: '', cantidad: '', iva: '' }]);
        } catch (error) {
          setAlert({ type: 'error', message: 'Error al cargar productos del local.' });
        }
      } else {
        setAvailableProducts([]);
      }
    };

    loadProducts();
  }, [selectedLocalForCreation]);

  const handleAddProductRow = () => {
    setProducts([...products, { idProducto: '', cantidad: '', iva: '' }]);
  };

  const handleRemoveProductRow = (index) => {
    const newProducts = products.filter((_, i) => i !== index);
    setProducts(newProducts);
  };

  const handleProductChange = (index, field, value) => {
    const newProducts = products.map((p, i) =>
      i === index ? { ...p, [field]: value } : p
    );
    setProducts(newProducts);
  };

  const handleShowDetails = (transaccion) => {
    console.log("Mostrando detalles de:", transaccion);
    setSelectedTransaction(transaccion);
  };

  const handleCloseDetails = () => {
    setSelectedTransaction(null);
  };

  const handleCreateTransaction = async () => {
    if (!selectedLocalForCreation || !transactionType || products.length === 0) {
      setAlert({ type: 'error', message: 'Por favor, complete todos los campos.' });
      return;
    }
    for (let prod of products) {
      if (!prod.idProducto || !prod.cantidad || !prod.iva) {
        setAlert({ type: 'error', message: 'Complete todos los campos de los productos.' });
        return;
      }
    }

    const payload = {
      idLocal: parseInt(selectedLocalForCreation, 10),
      tipoTrans: transactionType,
      productos: products.map(prod => ({
        idProducto: parseInt(prod.idProducto, 10),
        cantidad: parseInt(prod.cantidad, 10),
        iva: parseFloat(prod.iva)
      }))
    };

    setIsSubmitting(true);

    try {
      await crearTransaccion(payload);
      setAlert({ type: 'success', message: 'Transacción creada correctamente.' });
      setShowCreateForm(false);
      const updatedTransacciones = await getAllTransacciones();
      setTransacciones(updatedTransacciones);
      setSelectedLocalForCreation('');
      setTransactionType('');
      setProducts([{ idProducto: '', cantidad: '', iva: '' }]);
      setAvailableProducts([]);
    } catch (error) {
      setAlert({ type: 'error', message: 'Error al crear la transacción.' });
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleCancelCreation = () => {
    setShowCreateForm(false);
    setSelectedLocalForCreation('');
    setTransactionType('');
    setProducts([{ idProducto: '', cantidad: '', iva: '' }]);
    setAvailableProducts([]);
  };

  return (
    <div className="p-6 max-w-6xl mx-auto">
      <div className="bg-white rounded-lg shadow-lg p-6">
        <h1 className="text-2xl font-bold text-gray-800 mb-4">Transacciones</h1>

        {alert.message && (
          <div
            className={`p-4 mb-4 rounded-md ${
              alert.type === 'error' ? 'bg-red-100 text-red-700' : 'bg-green-100 text-green-700'
            }`}
          >
            {alert.message}
          </div>
        )}

        {isLoading ? (
          <div className="animate-pulse">
            <div className="h-6 bg-gray-200 rounded w-full mb-2"></div>
            <div className="h-6 bg-gray-200 rounded w-full mb-2"></div>
            <div className="h-6 bg-gray-200 rounded w-full mb-2"></div>
          </div>
        ) : transacciones.length > 0 ? (
          <div className="overflow-x-auto">
            <table className="w-full">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    ID Transacción
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    ID Local
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Tipo Transacción
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Fecha Creación
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Fecha Actualización
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Estado
                  </th>
                  <th className="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Acciones
                  </th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-200">
                {transacciones.map((transaccion) => (
                  <tr key={transaccion.idTransaccion} className="hover:bg-gray-50">
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                      {transaccion.idTransaccion}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                      {transaccion.idLocal}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                      {transaccion.tipoTrans}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                      {new Date(transaccion.fechaCreacion).toLocaleString()}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                      {transaccion.fechaActualizacion
                        ? new Date(transaccion.fechaActualizacion).toLocaleString()
                        : 'N/A'}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                      {transaccion.estado}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-center text-sm">
                      <button
                        onClick={() => handleShowDetails(transaccion)}
                        className="px-3 py-1 bg-blue-500 text-white rounded hover:bg-blue-600"
                      >
                        Ver detalles
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          <p className="text-gray-600">No hay transacciones registradas.</p>
        )}

        {!showCreateForm && (
          <button
            onClick={() => setShowCreateForm(true)}
            className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Crear Transacción
          </button>
        )}

        {showCreateForm && (
          <div className="mt-4 p-4 border rounded">
            <h2 className="text-lg font-semibold mb-2">Nueva Transacción</h2>
            <div className="mb-4">
              <label className="block mb-1">Local</label>
              <select
                value={selectedLocalForCreation}
                onChange={(e) => setSelectedLocalForCreation(e.target.value)}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg"
              >
                <option value="">Seleccione un local</option>
                {availableLocals.map((local) => (
                  <option key={local.idLocal} value={local.idLocal}>
                    {local.nombre}
                  </option>
                ))}
              </select>
            </div>
            <div className="mb-4">
              <label className="block mb-1">Tipo de Transacción</label>
              <select
                value={transactionType}
                onChange={(e) => setTransactionType(e.target.value)}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg"
              >
                <option value="">Seleccione el tipo</option>
                <option value="VENT">VENT</option>
                <option value="COMP">COMP</option>
              </select>
            </div>
            <div className="mb-4">
              <h3 className="text-xl font-semibold mb-2">Productos</h3>
              {products.map((prod, index) => (
                <div key={index} className="border p-4 mb-2 rounded">
                  <div className="mb-2">
                    <label className="block mb-1">Producto</label>
                    <select
                      value={prod.idProducto}
                      onChange={(e) => handleProductChange(index, 'idProducto', e.target.value)}
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg"
                    >
                      <option value="">Seleccione un producto</option>
                      {availableProducts.map((product) => (
                        <option key={product.idProducto} value={product.idProducto}>
                          {product.nombre}
                        </option>
                      ))}
                    </select>
                  </div>
                  <div className="mb-2">
                    <label className="block mb-1">Cantidad</label>
                    <input
                      type="number"
                      value={prod.cantidad}
                      onChange={(e) => handleProductChange(index, 'cantidad', e.target.value)}
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg"
                      placeholder="Ingrese la cantidad"
                    />
                  </div>
                  <div className="mb-2">
                    <label className="block mb-1">IVA</label>
                    <input
                      type="number"
                      value={prod.iva}
                      onChange={(e) => handleProductChange(index, 'iva', e.target.value)}
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg"
                      placeholder="Ingrese el IVA"
                    />
                  </div>
                  {products.length > 1 && (
                    <button
                      type="button"
                      onClick={() => handleRemoveProductRow(index)}
                      className="text-red-500 hover:underline"
                    >
                      Eliminar producto
                    </button>
                  )}
                </div>
              ))}
              <button
                type="button"
                onClick={handleAddProductRow}
                className="mt-2 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
              >
                Agregar otro producto
              </button>
            </div>
            <div className="flex space-x-2">
              <button
                onClick={handleCreateTransaction}
                className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600"
                disabled={isSubmitting}
              >
                {isSubmitting ? 'Creando...' : 'Guardar'}
              </button>
              <button
                onClick={handleCancelCreation}
                className="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600"
              >
                Cancelar
              </button>
            </div>
          </div>
        )}
      </div>

      {selectedTransaction && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white rounded-lg shadow-lg w-11/12 md:w-3/4 lg:w-1/2 p-6">
            <h2 className="text-xl font-bold mb-4">Detalles de la Transacción</h2>
            <div className="mb-2">
              <strong>ID Transacción:</strong> {selectedTransaction.idTransaccion}
            </div>
            <div className="mb-2">
              <strong>ID Local:</strong> {selectedTransaction.idLocal}
            </div>
            <div className="mb-2">
              <strong>Tipo Transacción:</strong> {selectedTransaction.tipoTrans}
            </div>
            <div className="mb-2">
              <strong>Fecha Creación:</strong> {new Date(selectedTransaction.fechaCreacion).toLocaleString()}
            </div>
            <div className="mb-2">
              <strong>Fecha Actualización:</strong>{' '}
              {selectedTransaction.fechaActualizacion
                ? new Date(selectedTransaction.fechaActualizacion).toLocaleString()
                : 'N/A'}
            </div>
            <div className="mb-2">
              <strong>Estado:</strong> {selectedTransaction.estado}
            </div>
            <div className="mb-2">
              <strong>Productos:</strong>
              {selectedTransaction.productos && selectedTransaction.productos.length > 0 ? (
                <ul className="list-disc ml-6">
                  {selectedTransaction.productos.map((producto) => (
                    <li key={producto.idProducto}>
                      <strong>{producto.nombre}</strong> - {producto.descripcion} - Código de Barras: {producto.codigoBarras} - PVP: {producto.pvp}
                    </li>
                  ))}
                </ul>
              ) : (
                <p>No hay productos asociados.</p>
              )}
            </div>
            <div className="flex justify-end mt-4">
              <button
                onClick={handleCloseDetails}
                className="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600"
              >
                Cerrar
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Transacciones;
