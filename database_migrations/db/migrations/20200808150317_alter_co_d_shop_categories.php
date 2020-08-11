<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class AlterCoDShopCategories extends AbstractMigration
{

    public function change(): void
    {
        $table = $this->table('shop_belongs_categories');
        $table->addColumn('disable_cod', 'boolean')->update();

    }
}
