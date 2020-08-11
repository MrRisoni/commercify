<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopCategories extends AbstractMigration
{

    public function change(): void
    {
        $shopCats = $this->table('shop_categories', ['signed' => false]);
        $shopCats->addColumn('title', 'string', ['limit' => 55])
            ->addIndex(['title'], ['unique' => true])
            ->create();
    }
}
