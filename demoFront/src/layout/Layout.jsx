import React from 'react'
import { Link, Outlet } from 'react-router-dom'

export default function Layout() {
  return (
    <div className="flex h-screen">
      <aside className="w-64 bg-gray-800 text-white p-4">
        <nav className="space-y-2">
          <Link to="/" className="block p-2 hover:bg-gray-700 rounded">
            Locales
          </Link>
          <Link to="/productos" className="block p-2 hover:bg-gray-700 rounded">
            Productos
          </Link>
          <Link to="/transacciones" className="block p-2 hover:bg-gray-700 rounded">
            Transacciones
          </Link>
          <Link to="/productosLocal" className="block p-2 hover:bg-gray-700 rounded">
            Productos por local
          </Link>
        </nav>
      </aside>

      <main className="flex-1 p-8 bg-gray-100">
        <Outlet />
      </main>
    </div>
  )
}