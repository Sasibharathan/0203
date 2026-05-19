import { Link } from "react-router";

type PageBreadcrumbProps = {
  pageTitle: string;
};

export default function PageBreadcrumb({ pageTitle }: PageBreadcrumbProps) {
  return (
    <div className="mb-6 flex flex-wrap items-center justify-between gap-3">
      <div className="min-w-0">
        <h2 className="truncate text-xl font-semibold text-gray-800 dark:text-white/90">
          {pageTitle}
        </h2>
        <nav className="mt-1 text-sm text-gray-500 dark:text-gray-400">
          <ol className="flex flex-wrap items-center gap-2">
            <li>
              <Link
                to="/"
                className="transition-colors hover:text-gray-700 dark:hover:text-gray-300"
              >
                Dashboard
              </Link>
            </li>
            <li className="text-gray-400">/</li>
            <li className="truncate text-gray-600 dark:text-gray-300">
              {pageTitle}
            </li>
          </ol>
        </nav>
      </div>
    </div>
  );
}

