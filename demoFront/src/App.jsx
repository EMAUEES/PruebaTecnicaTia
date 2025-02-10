import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Layout from './layout/Layout'
import Trasacciones from './components/Transacciones'
import Locales from './components/Locales'
import Productos from './components/Productos'
import ProductosLocal from './components/ProductosLocal'

export default function App() {
  return (
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Locales />} />
          <Route path="transacciones" element={<Trasacciones />} />
          <Route path="productos" element={<Productos  />} />
          <Route path="productosLocal" element={<ProductosLocal  />} />
        </Route>
      </Routes>
  )
};