<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableProductCategories extends AbstractMigration
{

    public function change(): void
    {
        $prodCats = $this->table('product_categories', ['signed' => false]);
        $prodCats->addColumn('title', 'string', ['limit' => 55])
            ->addIndex(['title'], ['unique' => true])
            ->create();
    }
}
