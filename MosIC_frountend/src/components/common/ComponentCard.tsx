import type { ReactNode } from "react";

type ComponentCardProps = {
  title: string;
  desc?: ReactNode;
  children: ReactNode;
};

export default function ComponentCard({ title, desc, children }: ComponentCardProps) {
  return (
    <section className="mt-6 rounded-2xl border border-gray-200 bg-white p-5 shadow-theme-xs dark:border-gray-800 dark:bg-white/[0.03] lg:p-6">
      <header className="mb-5 flex flex-wrap items-start justify-between gap-3">
        <div className="min-w-0">
          <h3 className="truncate text-lg font-semibold text-gray-800 dark:text-white/90">
            {title}
          </h3>
          {desc ? (
            <p className="mt-1 text-sm text-gray-500 dark:text-gray-400">
              {desc}
            </p>
          ) : null}
        </div>
      </header>

      {children}
    </section>
  );
}

